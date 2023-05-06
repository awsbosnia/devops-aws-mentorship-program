# 📝 Homework / Task assigment
## 📅 Date: 21.03.2023.

#### [TASK-5: Create AWS Account and IAM Users](https://github.com/allops-solutions/devops-aws-mentorship-program/issues/39)

In the Week-6 of our classes, we started learning about AWS. For this week we have the following tasks which will be used to prepare our AWS account for future use. This task is considered done when you can mark all subtasks as completed.

Account Owners need to create AWS Accounts.
If you see your name inside the document [DevOps Mentorship Program - AWS Account Owners](https://docs.google.com/spreadsheets/d/1OChZSLmr2Bh89L-bPKWPVUUAEKbTQEKWVD1PVNLUmKM/edit?usp=sharing) document in column **Account Owner** you need to create an AWS Account. Once when AWS Account is created do the following.

**Part 1:**
- [x] Create MFA for root user
- [x] Log out from your AWS account and log in back to verify that you can access using root user and your MFA code.
- [x] Give your account an alias `firstname-lastname-devops-mentorship`
- [x] Go into the `Account Settings` and allow **IAM User and Role Access to Billing Information**
- [x] Go into the `IAM Dashboard` and create `IAM User` for yourself, `IAM User` should be named `firstname.lastname`
- [x] IAM User should be part of the `IAM Group` called `Administrators`
- [x] IAM Group Administrators should have `AdministratorAccess` permissions attached
- [x] Inside IAM Dashboard, click on `Account settings` on the left-hand side and check `Password policy` for your account.
- [x] Login to your account using your IAM user instead of root user.
- [x] Create new IAM Group called `Tier2`. Do not attach any policies to that group.
- [x] Ping Dzenan or Boris via slack and ask them to send you AWS Credits, apply AWS Credits code into your AWS Account

You are now ready for part 2 of this ticket.
- [x] Check out the document [DevOps Mentorship Program - AWS Account Owners](https://docs.google.com/spreadsheets/d/1OChZSLmr2Bh89L-bPKWPVUUAEKbTQEKWVD1PVNLUmKM/edit?usp=sharing) and find which additional IAM Users you need to create.  IAM Users need to be in the format `firstname.lastname`.
- [x] Add them into `Tier2` IAM Group
- [x] Ping new users via slack and send them `.csv` access details for their IAM Users
- [x] Ask them to log in to AWS Account and verify access
- [x] Once when your IAM Users successfully log in into the AWS Account and confirm their access you can put a note into the excel file inside column `Account Owners Note`

[:fast_forward: Class Notes](/devops-mentorship-program/03-march/week-5-210323/00-class-notes.md)
[:fast_forward: Additional Reading](/devops-mentorship-program/03-march/week-5-210323/02-additional-reading.md)
[:fast_forward: HOME - README.md](../../../README.md)
[:fast_forward: Sadrzaj - DevOps Learning Path](../../../table-of-contents.md)
