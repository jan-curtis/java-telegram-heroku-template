Steps to deploy your java telegram bot to heroku

* git clone https://github.com/rsfedotov/java-telegram-heroku-template
* cd java-telegram-heroku-template/
* create new telegram bot using BotFather (https://telegram.me/botfather)
* obtain your new bot token and update file com.rsfedotov.handler.EchoBot.java
* Create your heroku app using heroku CLI (https://devcenter.heroku.com/articles/heroku-cli)
  * heroku create
  * Creating app... done, ⬢ forza-juve-000000
    https://forza-juve-000000.herokuapp.com/ | https://git.heroku.com/forza-juve-000000.git
  * copy "https://forza-juve-000000.herokuapp.com/" and execute
  * heroku config:set -a forza-juve-000000 APP_URL=https://forza-juve-000000.herokuapp.com 
  * git push heroku master

Big Thanks to https://github.com/pengrad/telegram-bot-heroku
  