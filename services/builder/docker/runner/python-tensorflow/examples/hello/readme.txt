Python for better or worse has found cemented itself as the lingua franca of data science. With its rise in popularity also comes how it is deployed. Simultaneously with the rise in Python has also been the rise in container deployments. Like Python, containers are being used in data science as well to run processes of all kinds. While data science is one popular use for Python it is not the only one, as there are many. Regardless of how Python is used, containerizing a Python app is relatively straightforward given that Python and Docker Hub provide a lot of automation to make this happen. Python has PyPi, the Python package manager that can used to install Python application dependencies. PyPi can use a manifest file that lists requirements to automate this process. Docker can invoke PyPi on build to produce a container image that has all of the dependencies and the application using these dependencies.

Imagine you’re trying to deploy the following Python code, contained in index.py. The application is a simple, “Hello World” app that uses Flask, a small HTTP server for Python apps.

index.py

from flask import Flask
app = Flask(__name__)
@app.route("/")
def hello():
    return "Hello World!"
if __name__ == "__main__":
    app.run(host="0.0.0.0", port=int("5000"), debug=True)
To do so, create a text file called Dockerfile in your application’s root and paste in the following code.

Dockerfile

FROM python:alpine3.7
COPY . /app
WORKDIR /app
RUN pip install -r requirements.txt
EXPOSE 5000
CMD python ./index.py
Note that FROM directive is pointing to python:alpine3.7. This is telling Docker what base image to use for the container, and implicitly selecting the Python version to use, which in this case is 3.7. Docker Hub has base images for almost all supported versions of Python including 2.7. This example is using Python installed on Alpine Linux, a minimalist Linux distro, which helps keep the images for Docker small. Prefer Alpine unless there’s a compelling reason to use another base image such as Debian Jessie.

Also note is the RUN directive that is calling PyPi (pip) and pointing to the requirements.txt file. This file contains a list of the dependencies that the application needs to run. Because Flask is a dependency, it is included as such in the requirements.txt with a simple reference. You can also select version libraries if you need specific versions with requirements.txt. The file should also be in the root of the application.

requirements.txt

flask
The remaining directives in the Dockerfile are pretty straightforward. The CMD directive tells the container what to execute to start the application. In this case, it is telling Python to run index.py. The COPY directive simply moves the application into the container image, WORKDIR sets the working directory, EXPOSE exposes a port that is used by Flask.

To build the image, run Docker build from a command line or terminal that is in the root directory of the application.

docker build --tag my-python-app .
This will “tag” the image my-python-app and build it. After it is built, you can run the image as a container.

docker run --name python-app -p 5000:5000 my-python-app
This starts the application as a container. The –name parameter names the container and the -p parameter maps the host’s port 5000 to the containers port of 5000. Lastly, the my-python-app refers to the image to run. After it starts, you should be able to browse to the container. Depending on how you are running Docker depends on what the IP address of the application will be. Docker for Windows and Docker for Mac will be able to use 127.0.0.1. For other instances, it will be the host IP of a VM or physical machine you are running Docker on.

Naturally, more complex scenarios will require more attention to details, but the basic flow is the same for most all Python apps. Putting it all together will enable containerized Python apps in short order though!