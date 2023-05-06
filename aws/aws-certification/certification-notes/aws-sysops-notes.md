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

