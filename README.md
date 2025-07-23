This project is an IoT-based smart manhole monitoring system using Raspberry Pi, DHT11, IR sensors, Tilt sensors, and Firebase for real-time alerts and monitoring. It also includes a UI for viewing manhole status.

🔧 Hardware Components
Raspberry Pi
DHT11 Temperature & Humidity Sensor
IR Sensor
Tilt Sensor
Water Level Sensor (Digital/Float)
Jumper Wires, Breadboard

Raspberry Pi GPIO Connection
| Sensor             | GPIO Pin (BCM) | Purpose                |
| ------------------ | -------------- | ---------------------- |
| DHT11              | 17             | Temperature & Humidity |
| IR Sensor          | 23             | Manhole lid detection  |
| Tilt Sensor        | 24             | Tilting detection      |
| Water Level Sensor | 18             | Water overflow alert   |


🧠 Backend - Raspberry Pi Setup
📁 File: manhole_monitoring.py
Functions:
Reads sensor data from GPIO
Pushes data to Firebase Realtime Database
Sends alerts using Firebase Cloud Messaging (FCM)



🔥 Firebase Configuration
Go to Firebase Console
Create a new project
Navigate to Project Settings > Service Accounts
Click Generate new private key
Save it as serviceAccountKey.json and place it on your Pi
Replace the following in your Python code:
python
Copy
Edit
cred = credentials.Certificate("/home/raspberrypi/Downloads/serviceAccountKey.json")
firebase_admin.initialize_app(cred, {
    'databaseURL': 'https://<your-database-name>.firebaseio.com'
})


🔁 System Flow
Sensors collect real-time data.
Raspberry Pi reads sensor data every 5 seconds.(Central Unit)
Data is pushed to Firebase Realtime DB.(Nodes)
Web UI auto-refreshes with latest readings.

If dangerous conditions are detected (tilt/open lid/high water), a push notification is triggered via FCM.

🔔 Alerts
Firebase Cloud Messaging is used to send alerts to subscribed devices when:
Lid is open (IR detection)
Manhole is tilted
Water level is high



🔮 Future Enhancements

->Multi-Sensor Data Handling with Apache Kafka
Integrate Apache Kafka to handle real-time data streams from multiple manhole sensors, enabling scalable and fault-tolerant data pipelines.

->LoRa Integration for Long-Range Communication
Use LoRa modules to transmit sensor data from remote manholes without relying on Wi-Fi or mobile networks, allowing for deployment in rural or underground areas.

->Edge ML for Anomaly Detection
Run lightweight machine learning models on Raspberry Pi to detect anomalies locally and reduce latency in alert systems.

->Mobile App Alerts
Create an Android/iOS application to notify municipal workers of manhole cap breaches or flooding in real-time.

->GPS-Based Monitoring
Add GPS modules to each sensor node to track manhole locations and movement for predictive maintenance.


