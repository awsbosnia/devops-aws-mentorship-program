# AWS SysOps Associate - Exam Notes  
Ovdje mozete pronaci biljeske sa ispita ljudi koji su vec izasli na ispit i polozili ga. Ideja ovih biljeski je da vam daju uvid u tip pitanja / servise koje trebate poznavati kako bi uspjesno polozili ispit.   

### Exam date: June 2021

CloudWatch event vs CloudWatch Alarm 
Which one to choose when (attention to SNS notification) Service Catalog 
All logs must be send to S3, but prevent ‘some’ team to create buckets - Allow sending logs? 
- Iam policy? 
EBS Volume type 
Which one to choose (base on performance needed, access frequency…) AWS Config 
Find which costs at most with 
(Service for costs) 
RDS to stay after delete of CF stack 
(DeletionPolicy or deletion-prevention parameter)? 
CloudFormation 
- Deploy to another region (use parameters) 
- vs ElasticBeanstalk 
- Share CF template 
Check logs 
- ELB logs 
- VPC flowlogs 
- Access logs 
File deleted in EBS 
- Recover file from snapshot? 
CacheHitRatio<60%, CacheUsed>90% 
Route53: Records (A, CNAME,...) 
SQS high utilization, where to check 
Cloudwatch,... 
Maintenance window 
Where is enabled
Patching AMI cause delay 
Elasticache - 2 approaches for improving performance 
Node instances, node type 
DB failure reasons 
LDAP -> use EC2 roles 
Policy bucket 
Session manager (what to allow to see instances) 
Install agent, IAM policy, allow traffic in security group 
ALB: Cross-siting 
NAT Gateway vs NAT instance 
For future uses: NAT gateway or large NAT instance 
Spot instance 
Application can tolerate interruptions 
Service control policy 
What is that? 
Status code 503 for accessing S3 bucket 
The team needs to start and stop instances but is not comfortable with configurations. What to do? 
Streaming service in us-east-1, delays in Asia? New prod env or CloudFront?

### Exam date: May 23, 2022

3 Lab Exams:
Lab:
You need to create 2 S3 Buckets: for data and logs
you need to setup needed configuration: versioning, lifecycle configuration, public access
Lab:
Lambda function and rds database are created and you need to modify the function to communicate with rds
You need to configure vpc, subnet, sg
You need to attach corresponding execution role
You need to update env variable for rds proxy
Setup rds proxy idle timeout 3 min
Lab
You need to create vpc with defined CIDR, public and private subnets
Create publicSG and privateSG with defined inbound rules
Also you need to create route table
All ip traffic needs to be logged in S3 bucket

Questions:
Parameter InstanceLimitExceed what means
Lambda function is created in acc A and s3 bucket is created in acc B. What you need to set up to enable  lambda function to access data on the s3 in acc B?
You use CF which launches EC2 instances from the ami in different regions. It works in one region, but in another one it is not working. How to resolve this issue?
You have CF which creates a lambda function, dynamodb table and s3 bucket. You want to delete the stack and all create resources except the dynamodb table. What do you need to use in this situation?
AWS Organizations and scp
Your ec2 instance has failed both status checks. What do you need to do?
You are using RDS and got “too many connection errors”. How to resolve this?
Memcached and low latency issue. How to resolve it?
Cloudfront, mobile users got the desktop version of the app. How to resolve it?
You have an app running on your ec2 and it cannot access on-prem server- Unresolvable domain error. How to resolve (answers: use R53 resolver with different outbound and inbound rules you need to choose correct rules)
You have doman example.com and subdomain www.exemple.com. Also, you want to use a static website. How many bucket will you create and which names you will use)
ALB, Cloudfront. First time when users use the website, everything is working, but when they refresh, they get an error. 
How to restrict access for cloudtrail logs
The user wants to use AWS CLI with services, but it is not working. What is wrong?
Aws control tower

### Exam date: September 16, 2022.

Lab Exams:
Cloudwatch metric: 
Create and set metric for log group.
Create alarm for metric. 
Filter serverCode5xx

WAF:
Create rule for SQL injection.
Create IP pool. 
Restrict IP pool. 
Rate based rule on 100 / 5 minutes on matching string “ /login “.
Turn off metric for specific rule.
Associate ACL with Load Balancer. 

Spot Request:
Use launch template.
Set VPC.
Set Availabilty Zone.
Set SSM Automation.

The questions I had on the exam are pretty much the same as two above exams. 

 
### Exam date: Oct, 2022.

Lab Exams:
Cloudwatch metric: 
Create and set metric for log group.
Create alarm for metric. 
Filter serverCode5xx

WAF:
Create rule for SQL injection.
Create IP pool. 
Restrict IP pool. 
Rate based rule on 100 / 5 minutes on matching string “ /login “.
Turn off metric for specific rule.
Associate ACL with Load Balancer. 

Spot Request:
Use launch template.
Set VPC.
Set Availabilty Zone.
Set SSM Automation.

The questions I had on the exam are pretty much the same as two above exams +

-cloudformation-deploy on multiple regions
-cloudformation-keep existing resources
-RDS proxy
-protect multiple S3 buckets (disable public access) ali ne na ruke
- s3 is public, but also CF is used. How to protect?
-horisontal&vertical scaling
-missing nat route in routetable, where to put it

### Exam date: OCTOBAR 25 2022.

Lab2:
S3 public access
Static website hosting
Editovanje a recorda za alb
Podesavanje failovera primary i seconday u route53 izmedju s3 i elb

Lab3
Cloudformation ima vec templlate
Editovati ga:
Promjeniti sa t.2 na t.3
Rolu promjenit u roleB sa roleA
Dodati ssh port za ip adresu neku na sg dev ec2 instance
Updejtovati stack sa time ujljuciti deletion protection i koristiti neki poseban role za deploymen
Output prod id iinstance proslijediti na kraju laba


### Exam date: Apr, 2023.

Lab Exams:
As of 28.3.2023, lab exams are not included until further notice. The exam consisted of 65 multiple choice questions.

CloudFront and Route 53
A lot of questions were related to these two, such as DNS record types, and communication between CF/R53 and ELB, ASG and EC2 servers (example question I had - How would I set up failover with a R53 health check to two ELBs in different regions)

Monitoring and reporting
How to get access logs for ELB, VPC, S3, how to create a report out of them.

High availability and redundancy
A lot of questions were focused on establishing high availability with ASG, Multi-AZ DB failovers for Aurora DB, S3 backups that needed quick retrieval and Global Network failovers, such as R53 routing and failover.

Session Manager
A particularly high number of questions had to do with SSM, how to troubleshoot it if it doesn't work (permissions and SSM agent), how to use it to automate processes, how to run one-time scripts on it etc.

VPC
A couple of questions were related to NACLs, Security Groups, ephemeral ports and IPv4/IPv6 routing

### Exam date: June 16, 2023.


- Following services were featured on the exam:
- cloudformation -> cfn-init, cfn-signal, stacksets, drift detection
- r53 -> which type of policy would you choose given the scenario, in my case there was geo-proximity, geolocation and failover
- ec2 placement groups -> cluster and partition in my case
- ec2 status checks -> both system and instance status checks
- given the scenario, what should we do with the AMI -> copy or share it and what that process embodies
- a few questions about system manager -> automation documents with AWS Config for automatic monitoring and remediation as well as run document option when we want to run script across ec2 instances
- s3 -> many questions about s3 versioning, bucket policies, storage classes (with Glacier and vault), s3 select, s3 inventory, multi-part upload, athena, s3 encryption, s3 MFA delete, s3 access points, s3 CORS, s3 lifecycle policies
- CloudFront -> many questions regarding the scenarios such as what we should do to increase the cache ration in Edge Locations
- amazon aurora -> question or two
- cw alarms, cw log groups, EventBridge, cloudTrail
- aws organizations, aws budgets, aws Health Dashboard
- amazon macie, trusted advisor, kms encryption using customer managed keys, aws artifact
- secret manager and parameter store
- many questions about VPC and networking in general as well as troubleshooting networking issues -> NAT Gateway, Internet Gateway, ipv4 + ipv6 CIDR blocks, public vs private subnet, egress-only-internet gateway, VPN, Direct Connect, NACLs, Security Groups, ephemeral ports etc.
- a question or two about DynamoDB
- AWS WAF + DDos protection + Network FireWall
- CloudHSM
- questions about ALB and NLB -> mostly questions about listeners, how to add SSL/TLS, which one should when we need insane performances (NLB) or capabilities to inspect the request and based on it route to specific endpoint (ALB)
- AWS Certificate Manager (ACM)
- Cognito user pools vs identity pools + Identity Federation -> I think only one or two questions
- VPC endpoints
- AWS Config -> a few questions
- EBS volumes with DLM
- Storage Gateway


Questions I remember from the exam:


1. What is happening when you deploy a stackset and it is in state OUTDATE? -> it means it is deploying a stack to that specific region
2. What is happening when you deploy a stackset and it is in state OUTDATE? -> it means it is deploying a stack to that specific region
3. How to achieve lowest latency and highest networking capabilities between ec2 instances? -> cluster placement groups
4. SSM agent is installed on ec2 instances, but the instance is still not under the management of the SSM? -> we need to attach the policy to the EC2 instance
5. We want to track who accesses our ALB, what should we use? -> we can use the ALB's tracing and logging capabilities
6. How to get info on the memory utilization on EC2 instances as well as intervals as low as 1 minute? -> use CloudWatch agent and enable detailed monitoring (both)
7. If we do not want to hardcode the DB credentials in the code, where should we store it if we want to be able to rotate the credentials automatically and what do we need more to be able to make this solution work? -> Secret Manager and IAM ROLE attached to EC2/Lambda
8. A screenshot of the VPC Flow Log record and you are supposed to determine what went wrong ->
e.g:
2 123456789010 eni-1235b8ca123456789 172.31.16.139 172.31.16.21 20641 22 6 20 4249 1418530010 1418530070 ACCEPT OK
2 123456789010 eni-1235b8ca123456789 172.31.9.69 172.31.9.12 49761 3389 6 20 4249 1418530010 1418530070 REJECT OK
please check: https://docs.aws.amazon.com/vpc/latest/userguide/flow-logs-records-examples.html
9. We are using Lambda function with RDS and we get a "TooManyConnections" error, what should we do to resolve this issue -> use RDS proxy which will manage connection pools and clean up idle connections
10. A question regarding the AWS Cost Allocation Tags
11. How to connect an EC2 to s3 from VPC without traversing the public internet? -> VPC Gateway endpoint