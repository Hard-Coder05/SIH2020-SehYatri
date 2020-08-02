# Team X SIH 2020
Team X's official repository for Smart India Hackathon'20 problem statememt AJ128.

## Backend:

We've created the backend of our project in Django using the Django's predefined User Model and Heroku is used for deployment. For the database, we're using PostgreSQL. Currently, the backend supports user registration and login only. 

### Setup :
  - Create a virtual environment with Python3.7: virtualenv env -p python3.7. If you dont have python3.7 yet then you can install it with:
  linux(ubuntu/debian) - sudo apt install python3.7
  windows - Download installer from https://www.python.org/downloads/release/python-370/.
  - Activate the virutal environment: source env/bin/activate
  - Install all the dependencies in requirements.txt file: pip install -r requirements.txt
  - Migrate the migrations: python manage.py migrate
  - Run the app: python manage.py runserver
  - Navigate to http://localhost:8000 in your browser
  - When you are done using the app, deactivate the virtual environment: deactivate

The **API** for registration and login is served on the heroku app at :  http://whispering-harbor-50094.herokuapp.com/docs

## IoT :

We are using Arduino Uno microcontroller board to send fuel tank level sensor data to the android app via bluetooth.

### The components used :

1. Arduino Uno
2. Ultrasonic Sensor (fuel level sensor)
3. Bluetooth Module (HC-05)
4. Jumper Wires
5. USB Cable
6. Arduino IDE (to upload program to the board)

You can download the Arduino IDE from: https://www.arduino.cc/en/main/software

### Steps :

1. Connect the IoT components.
2. Download and install arduino ide.
3. Copy the source code from IoT folder.
4. Connect the arduino to PC via usb cable.
5. Compile and upload the code to the board.
6. Open the ide console to view the output values.
7. Connect our android app to the bluetooth module.
8. Voila! You have the fuel tank level values on your mobile app.

