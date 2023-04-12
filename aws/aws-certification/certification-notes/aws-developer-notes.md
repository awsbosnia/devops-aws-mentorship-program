# AWS Developer Associate - Exam Notes  
Ovdje mozete pronaci biljeske sa ispita ljudi koji su vec izasli na ispit i polozili ga. Ideja ovih biljeski je da vam daju uvid u tip pitanja / servise koje trebate poznavati kako bi uspjesno polozili ispit.   

### Exam date: April, 2021.

Elastic Beanstalk:
Deployments: All at once, Rolling, Green/Blue

Kinesis:
Shards, resharding, number of instances to consume shards, number of records per instance, (num of instances <= num of shards), scale based on what (CPU of instance, not shards). 

Kinesis Data Stream vs Kinesis Data Firehouse (couple of question when you need to choose one of them from options).

CloudWatch (Logs, Alarms - integrate with SNS, Lambda), CloudEvent (scheduled events that can be triggered e.g. each 10 min, 1 hour...), CloudTrail. Usually, not difficult questions, know when to use which one. Be careful with options (e.g. CloudTrail alarm, CloudWatch Log agent)

Cognito (user pools - create new user for sign in, identity pools - what user can do, what credentials to give to the user, assumeRoleWithIdentity, SAML and 3rd party (web) identity provider)

Load balancing, autoscaling - few questions, know the concept (HA, FT examples)

DynamoDB:

Streams as trigger for Lambda
Transactions - TransactionWriteItem
IAM controlling access (which policies to include, which actions to allow)
RCU/WCU - how much do you need, notice when they need to increased, autoscaling capacity (on-demand)
DAX - caching memory, read performance, eventually consistency read questions - use DAX
TTL - for Dax and DynamoDB
Exponential backoff - know when it is used
GSI and LSI - which attribute to use for partition key PK and which one for sort key SK (based on operation - query and scan)
How to improve scan operation (one answer was parallel scan)

Lambda:
Optimize runtime with using: handler, layers, memory (know how to optimize it with this, in which cases)

Elasticache:
Memcache and Redis: when to use one of them
Lazy loading and write-through

API gateway
Few questions but tough 
Import API (swagger), deploy API (watch out for changes), create corresponding actions in API

RDS: read replica and multi-AZ  

CI/CD
CodeCommit
CodeBBuild 
CodeDeploy
CodePipeline

Knowing basics will be enough, no difficult questions appeared. Also, recognize when to use, even in a combination with 3rd party. 

CloudFormation
Beside the basics (IaaC,...),		`Functions: Fn!`, `!GetAttr`

S3
Encryption (S3, KMS) when to use each one. Who is the key owner? Who stores the key, who does encryption. Client side encryption also - if no AWS included in encryption and users are providing a key.
PresignedURL  (access with temporary cred.)

KMS questions were a part of encryption of some another service (e.g. S3)

CloudFront: example were CloudFront is an option, choose it when it is needed

### Exam date: November, 2022.

There wasn’t anything new as the above exam, totally the same services.
