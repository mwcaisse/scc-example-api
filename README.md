# Spring Cloud Contracts UI/API

Example project to show contract testing between a producer and a consumer. Where the
producer is a Spring Boot API and the consumer is a single page web application.

## Domain
The domain of the example is a simple API that exposes the ability to manage recipes. Which 
exposes actions to:
* Create a new recipe
* Fetch a recipe by ID
* Delete an existing recipe
* Add a step to a recipe
* Delete a step from a recipe
* Add an ingredient to a recipe
* Delete an ingredient from a recipe


## Contracts
A set of contracts is defined for each of the API endpoints exposed, and the variations
of responses of those endpoints the consumer might care about.

For example the endpoint to add a step to a recipe can be expected to result in one of the following:
* The recipe the step is to be added to does not exist, returns a recipe not found result.
* The step that is to be added does not follow validation rules for a step, returns a step invalid result.
* The recipe exists, the step is valid, and the step was created successfully, returns a success result.

Therefore there will be three contracts for that endpoint, one describing how the producer
will handle each of those different cases. 

These contracts form the shared understanding between the producer, and the consumer of how the APIs will
behave. Both the consumer and producer can then use the contracts to ensure their application conforms to this
understanding


## Producer Testing
The producer will test its API against these contracts to ensure that it behaves as described in the contract. If the
producer makes a change that violates one of the contracts, the test for that particular contract will fail. Alerting 
the producer that they have changed the expected behaviour of their API.

The spring cloud contracts plugin will automatically generate unit tests for the producer.

## Consumer Testing
The consumer will utilize contract testing to ensure that the understanding on how the API is to behave doesn't change
in a way that is detrimental for it to perform its duties. If the producer makes a change to their API that negatively
affects how their application will work, a unit test will fail.

Unfortunately there isn't a spring library that will automate the unit testing in javascript. The standalone stubrunner
spring makes available makes it quite easy.

Assuming that we have a `RecipeService` class that defines a client for consuming the producers API. For this example
we are going to focus on the endpoint to add a step to a recipe.
```javascript
class RecipeService {

    constructor(baseUrl = defaultBaseUrl) {
        this.proxy = new Proxy(baseUrl);
    }
    
    addStep(id, step) {
        return this.proxy.post(`recipe/${id}/step/`, step);
    }
}
```

We can write tests as follows:
```javascript
describe("addStep", () => {

    test("shouldReturnBadRequestIfStepInvalid", async () => {
        try {
            await recipeService.addStep(1, {
                order: 1,
                description: null
            });
            expect(true).toBeFalsy();
        }
        catch (e) {
            expect(e.response.status).toBe(400);
        }
    });

    test("shouldReturnNotFoundRecipeDoesNotExist", async () => {
        try {
            await recipeService.addStep(500, {
                order: 1,
                description: "Peel all dem potats"
            });
            expect(true).toBeFalsy();
        }
        catch (e) {
            expect(e.response.status).toBe(404);
        }
    })

    test("shouldReturnOkIfValidStepWasCreated", async () => {
        let step = await recipeService.addStep(1, {
            order: 1,
            description: "Peel all dem potats"
        });

        expect(step).toBeDefined();

        expect(step.id).toBeDefined();
        expect(step.order).toBeDefined();
        expect(step.description).toBeDefined();
    })
});
```
We've defined one test for each of the three expected behaviors. It is important to note that we aren't performing a
test of exactly what the endpoint returns, but rather the behaviors that it exhibits. For each of the three behaviours:

### Returning Bad Request with Invalid Step
As a client that allows users to create new steps, I want to be able to inform them if the step they entered is invalid.
In order to do that I need to be able to determine the response from the API that indicates this. In this case the server 
will return a  response status of 400 when this happens.

When I send the request mocking a bad request, then I am able to determine that this is a bad request and differentiate 
it between different bad responses. If the producer changes the way they indicate the request is bad, and the assumption
that I can rely on the Bad Request response changes, then the test will fail, alerting me to this fact.

In the future the producer might add more information to the response. For example a list of validation errors, to allow
me to provide a more specific reason why it failed to my users. However, I am not implementing this beyond a simple
"Your request is invalid, therefore I am not going to test for anything more than what lets me do that.

### Should Return Not Found Recipe Does Not Exist
This is similar to returning bad request if the step is invalid, with the exception that the behaviour I would want
to take if this happens is different. I would like to inform my user that the recipe they are currently viewing no
longer exists. Then either navigate them away from that recipe or re-create it.

The main thing I want to be able to discern is that I am still able to determine that recipe doesn't exist. 

### Should Return Ok if Step was Created
As a client that allows users to create new steps, I want to be able to inform them that their step was successfully 
created and show them the updated step. In order to do that I need to be able to determine what a successful response is
and ensure the fields I need to display the new step to the user are included in the response.

When I send the request mocking a successful request, then I am able to determine that is a successful request, and it has
the fields I need in the response. If the producer changes how they indicate a successful response or remove a field
I rely on, then the test will fail, and I will be alerted.

In this case I listen on the `id`, `order`, and `description` fields. These are the fields that I am displaying to the
user. If the producer also returns `createdDate`, `modifiedDate`, or other fields, and removes or renames them in the
future, my test will continue to pass. Which is the desired behavior as those changes do not affect how I am using the 
API.

I could take this a step forward and check for more than just the presence of the fields, but also the format of the
field. For example maybe `id` starts being returned as a string, but my application is parsing it as a number. I would
want to be informed here that it is now a string.

## Running Projects

### API

To generate the contract tests and execute the contract tests simply run
```bash
gradle assemble
```

### UI

To run contract tests

First start the stubrunner.
```bash
./run_stubrunner
```
Note: If you are getting a git auth error, this assumes that you have SSH auth for github.com setup. Your SSH key
might need to be added to your SSH agent explicitly:
```bash
ssh-add -K <path-to-private-key>
```

Then run the tests
```bash
yarn test
```