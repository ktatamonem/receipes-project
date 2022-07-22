# receipes-project
sample project to manage your recipes , you can create, edit , delete and search all of your recipes also you can manages your ingredients .
# technology used
* JAVA 11
* spring boot 2.7.1
* OpenAPI 1.6.9
* angular 13
* Docker
# How to Run
* First of all make sure you have docker installed in your machine
* Go to recipes-BE
* run this command : docker build -t recipes:latest .
* Go back to recipes-fe
* Run this command : docker build -t recipes-ui:latest .
* Go back to recipes-project folder
* run this command : docker-compose up
* After the docker image started try to enter http://localhost:4200
* Login with the following credentials { login:demo , password:demo}
* You can now access to the application you can do the following operation : (Create ,Edit ,Delete , View) Ingredients - (Create, Edit ,Delete ,View , Search) Recipes
# For further details
* [Readme](https://github.com/ktatamonem/receipes-project/blob/main/receipes-BE/README.md)
