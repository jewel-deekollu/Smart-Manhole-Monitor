# manhole_monitoring.py

import adafruit_dht
import RPi.GPIO as GPIO
import time
import board
import firebase_admin
from firebase_admin import credentials, db, messaging

# ------------------------
# GPIO Pin Configuration
# ------------------------
tilt_pin = 24
ir_pin = 23
water_level_pin = 18

GPIO.setmode(GPIO.BCM)
GPIO.setup(tilt_pin, GPIO.IN)
GPIO.setup(ir_pin, GPIO.IN)
GPIO.setup(water_level_pin, GPIO.IN)

# ------------------------
# DHT11 Sensor Setup
# ------------------------
dht_device = adafruit_dht.DHT11(board.D17)

# ------------------------
# Firebase Initialization
# ------------------------
cred = credentials.Certificate("/home/raspberrypi/Downloads/serviceAccountKey.json")  # Replace path accordingly
firebase_admin.initialize_app(cred, {
     'databaseURL': 'https://manholemonitor-c768768-default-rtdb.firebaseio.com'  # Replace with your Firebase URL
})

# ------------------------
# Sending Push Notifications
# ------------------------
def send_push_notification(message):
    notification = messaging.Notification(
        title="Manhole Alert",
        body=message
    )
    message = messaging.Message(
        notification=notification,
        topic="manhole"
    )
    try:
        response = messaging.send(message)
        print(f"Notification sent successfully: {response}")
    except Exception as e:
        print(f"Error sending notification: {e}")

# ------------------------
# Reading DHT11 Sensor Data
# ------------------------
def read_dht_sensor():
    try:
        temperature = dht_device.temperature
        humidity = dht_device.humidity
        if humidity is not None and temperature is not None:
            return temperature, humidity
        else:
            return None, None
    except RuntimeError as error:
        print(f"DHT Error: {error.args[0]}")
        return None, None

# ------------------------
# Main Logic Loop
# ------------------------
try:
    while True:
        temperature, humidity = read_dht_sensor()
        water_level = GPIO.input(water_level_pin)
        ir_state = GPIO.input(ir_pin)
        tilt_state = GPIO.input(tilt_pin)

        data = {
            'temperature': temperature if temperature else "N/A",
            'humidity': humidity if humidity else "N/A",
            'water_level': 'High' if water_level == GPIO.HIGH else 'Low',
            'ir_status': 'Open' if ir_state == GPIO.LOW else 'Closed',
            'tilt_status': 'Tilted' if tilt_state == GPIO.LOW else 'Stable'
        }

        db.reference('manhole_status').set(data)
        print("Data pushed to Firebase:", data)

        if data['ir_status'] == 'Open' or data['tilt_status'] == 'Tilted' or data['water_level'] == 'High':
            message = f"Alert! IR: {data['ir_status']}, Tilt: {data['tilt_status']}, Water: {data['water_level']}"
            send_push_notification(message)

        time.sleep(5)

except KeyboardInterrupt:
    print("Program stopped by user.")

finally:
    print("Cleaning up GPIO...")
    GPIO.cleanup()
