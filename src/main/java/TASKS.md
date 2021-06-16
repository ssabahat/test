# The Ideas Engineering Backend Coding Challenge
## 1. Writing
### Task 1.1
What’s your proudest achievement?
It can be a personal project or something you’ve worked on professionally. Just a short paragraph is fine, but we’d love to know why you’re proud of it.



### Task 1.2
Tell us about a technical book or article you read recently. Why did you like it, and why should we read it as well?
- I follow ThoughtWork radar, to broaden my technology depth. Its a great platform to get insight about what is working in enterprise technology and engineering excellance

## 2. Coding
Let's start with the practical part.
While the challenge is framework-agnostic, we do have a few general guidelines:

* Programming language: **Java** (>=11) or **Kotlin** (>=1.4).
* Build tool: **Maven** or **Gradle**.
* We want you to demonstrate modern usage of a framework or libraries. This includes typical patterns and work flows like **DI**/**IoC**, **ODM**/**ORM**, **MVC**, **testing**, etc.
* Create a commit for each task that delivers runnable software. The commit message should be prefixed with the task number.
* Make sure that your software works as expected by writing tests where you think it is necessary.
* Regarding feature completeness - we strive for quality over quantity
* Your implementation should be easily readable and understandable by its design (e.g. package structure / explicit domain concepts / conventions)

#### Domain
When working in the field of media, you often come across concepts like news articles, authors, assets (e.g. videos or photos), article relevancy, recommendations and more.

### Task 2.1 - Basic News Article functionality
Implement an application that takes the following into account:

* Our application models the domain of a `NewsArticle`. 
* In order to manage news articles our service must provide a REST-ful interface.
* A `NewsArticle` can have the following details: `id, title, text, creationDate`. 
* A `NewsArticle` consists at least of a `title` and `creationDate`.

Implementation requirements:
* The application has to listen for HTTP requests on port 8080 and provide the following endpoints:
    * `NewsArticle` creation
    * Fetching a specific `NewsArticle`
    * Modify content (`title`, `text`) of a `NewsArticle`
    * Fetching all `NewsArticle`s
    
* Implement a "mocked", in-memory persistence (e.g. hash map / similar) for `NewsArticle`s, in order that they can be saved and served at runtime.
* Your implementation should be easily readable and understandable by its design (e.g. package structure / explicit domain concepts).
* Provide basic documentation as you see fit and usually deliver at work. We will definitely need the following:
    * How does one build and start the application in a *nix environment (or in a container).
    * How does one use your application using CURL commands.

### Task 2.2 - Persistence Layer
Refactor and extend the application you've implemented in `Task 2.1` in order to add a persistence layer.

* The persistence layer should be based on a publicly available and widely used DB.
* The DB has to be either embedded in the solution or run in a docker container.

### Task 2.3 - News Article relevance
Your application should be able to determine the relevance of an article according to the following business rules:

There are 3 classes of article relevance - `STANDARD`, `HOT` and `BORING`. 
This information should be determined by our app and delivered by its REST API in a field `relevance`, 
according to the following business rules: 

Relevance | Condition
--- | ---
`HOT` | The text contains more explanation marks (`!`) than full stops (`.`) and the article was created within the last minute.
`BORING` | The text contains more commas (`,`) than full stops (`.`) and the article was created within last 5 minutes.
`STANDARD` | otherwise

## Task 3 - The Path To Production

The solution you implemented must go to production and further development life cycle. It has to be publicly available and other team members will implement additional features. 
 
How would you do it? Please provide some bullet points of your approach.

1. Based on the expected load we are going to configure an autoscaling group on which our service is going to be deployed and we load balance this.
2. For deploying we are going to build CI/CD pipeline (Moonbeam, jenkins etc)
3. Logging of our service is not there. We can use splunk(Along with kafka) or elk for log insights.
4. Health Monitoring of this service is a must ( New relic or amazon cloud watch).
5. This service is going to be a part of other backend microservice. So, we need to register this service on API gateway (Eureka).
6. More unit testing.
7. Acceptance testing.
8. Load Testing , Soak Testing , Stress Testing (Apache JMeter)
9. Authentication and Authorization (if required) (Atleast for publisher of article)
10. Rate Limiting (On API gateway)
11. HTTPS instead of http.
12. SSL termination (At API gateway).
13. Caching for Hot Articles. (Cold storage for old and irrelevant articles).
14. Sharding of database (Right now we have implemented on single instance).
15. CDN caching.
16. Self-service UI for publisher of article.
17. UI for viewing the article.



