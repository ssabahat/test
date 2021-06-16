### Task 1.1
What’s your proudest achievement?

- I recently lead a project in which I replace splunk and built our in-house log management system. The problem was that splunk does a lot of preprocessing while injecting log into the system which causes delay on ingestion. We completely remove this preprocessing (speed up ingest cost) and built a sort of a database to handle querying. I was the only person in this project in the beginning and there was a lot of pushback from higher management because splunk is already a very trustworthy log mangement system.
But I persisted because I thought, it was the right approach. After prototyping, we got the green signal to implement the approach design by me. We reduce the time of ingest from 5-10 minute to 2 second.

### Task 1.2
Tell us about a technical book or article you read recently. Why did you like it, and why should we read it as well?
- I follow ThoughtWork radar, to broaden my technology depth. Its a great platform to get insight about what is working in enterprise technology and engineering excellance

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
18. Exception Handling is not that great right now. I would have worked on it a little more.

