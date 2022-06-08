# Website Deployment

## Heroku

We use [Heroku](https://dashboard.heroku.com/apps) to deploy our website, so the coaches of Team-NL can use our website whenever they want.

## Pipeline

We wanted to automate the deployment of the website. This means that we do not want to manually deploy the website everytime we push stuff to the website through git. So we automated this process using the pipeline in gitlab. We made this pipeline using [this guide](https://gitlab.fdmci.hva.nl/se-ewa/deployment-workshop/-/blob/master/doc/general/gitlab-cicd/README.md). While doing so we encountered some problems. First we personalised it to link to our own repository. Which made the file look like this.

```yml
stages:
  - deploy

deploy_be:
    stage: deploy
    image: node:latest
    tags:
        - hva
    only:
        refs:
            - heroku-deployment
        changes:
          - "bmx-app-backend/**/*"  
    script:
        - git remote rm heroku-be-app || true
        - git remote add heroku-be-app https://heroku:$HEROKU_API_KEY@git.heroku.com/bmx-nl-app-be-staging.git || true
        - git subtree split --prefix bmx-app-backend -b splitting-staging-be
        - git push --force heroku-be-app splitting-staging-be:master
        - git branch -D splitting-staging-be

deploy_fe:    
    stage: deploy
    image: node:latest
    tags:
        - hva
    only:
        refs:
            - heroku-deployment
        changes:
          - "bmx-app/**/*"  
    script:
        - git remote rm heroku-fe-app || true
        - git remote add heroku-fe-app https://heroku:$HEROKU_API_KEY@git.heroku.com/bmx-nl-app-staging.git || true
        - git subtree split --prefix bmx-app -b splitting-staging-fe
        - git push --force heroku-fe-app splitting-staging-fe:master
        - git branch -D splitting-staging-fe
```

However, the pipeline still failed, giving us the following error: 'Your account has reached its concurrent builds limit.' This meant that we had too many builds running on our heroku webdeployment. So we had 'restart all dynos' on heroku, after that the front-end fully worked. However the back-end was still a problem, giving us this error: 'Branch 'splitting-staging-be' is not an ancestor of commit '5065e2353eaa04260c8f34a178ee9138fd402d6b'.' The front-end gave us the same problem a couple of commits later. We still haven't found a way to solve this issue. 