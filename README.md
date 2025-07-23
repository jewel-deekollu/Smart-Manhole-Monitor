# 🚨 Smart Manhole Monitoring System

An IoT-based **Smart Manhole Monitoring System** designed using Raspberry Pi and multiple sensors. This project focuses on real-time hazard detection (lid tampering, water overflow, tilt) and pushes instant alerts using Firebase. It’s built for cities aiming to move toward smarter, safer infrastructure.

---

## 🔧 Hardware Components

| Component               | Purpose                         |
|------------------------|---------------------------------|
| 🖥️ Raspberry Pi        | Main controller unit            |
| 🌡️ DHT11 Sensor         | Temp & Humidity readings        |
| 📟 IR Sensor            | Detects manhole lid status      |
| 🌀 Tilt Sensor          | Detects manhole tilt            |
| 🌊 Water Level Sensor   | Detects potential overflow      |
| 🧰 Breadboard & Wires   | For circuit connections         |

---

## 🧩 GPIO Pin Mapping

| Sensor            | GPIO (BCM Pin) | Description                  |
|------------------|----------------|------------------------------|
| DHT11             | 17             | Reads temperature & humidity |
| IR Sensor         | 23             | Detects if lid is open       |
| Tilt Sensor       | 24             | Detects manhole tilt         |
| Water Sensor      | 18             | Detects high water levels    |

---

## 🧠 Backend: Raspberry Pi Logic

- Python script `manhole_monitoring.py` does the following:
  - Reads values from all connected sensors.
  - Sends sensor data to Firebase Realtime Database every 5 seconds.
  - Pushes alerts using Firebase Cloud Messaging (FCM) when critical conditions are detected.

---

## 🔥 Firebase Setup Instructions

1. Go to [Firebase Console](https://console.firebase.google.com)
2. Create a new Firebase project.
3. Under **Project Settings > Service Accounts**, generate a new private key.
4. Save it as `serviceAccountKey.json` on your Raspberry Pi.

**Edit the code accordingly:**

```python
cred = credentials.Certificate("/home/raspberrypi/Downloads/serviceAccountKey.json")
firebase_admin.initialize_app(cred, {
    'databaseURL': 'https://<your-database>.firebaseio.com'
})
````

---

## 🔁 How It Works

1. Sensors gather live data every 5 seconds.
2. Raspberry Pi collects and sends this data to Firebase.
3. Web dashboard reflects the current status in real-time.
4. If any sensor flags a risk (e.g., open lid, tilt, or water overflow), a **push notification** is sent out via FCM.

---

## 🔔 FCM Alert Conditions

| Trigger          | Condition                       |
| ---------------- | ------------------------------- |
| 🔓 Lid Open      | IR sensor detects open state    |
| ⚠️ Tilt Detected | Tilt sensor angle is abnormal   |
| 🌊 Overflow Risk | Water level sensor is triggered |

---

## 🌐 Frontend - Dashboard Highlights

* Firebase-backed status updates.
* Auto-refresh for real-time readings.
* Extendable to mobile/tablet/kiosk views.
* Easy to integrate with other municipal dashboards.

---

## 🚀 Possible Enhancements

### 📡 Long Range with LoRa

* Add **LoRa modules** for low-power, long-range data transmission without Wi-Fi dependency.

### 🧠 Edge ML for Smart Alerts

* Deploy lightweight ML models on Pi to predict unusual patterns and minimize false positives.

### 📲 Android/iOS Integration

* Create a mobile app for municipal field staff to receive live push alerts.

### 📍 GPS Tracking

* Integrate GPS modules to track physical location of each manhole in case of displacement.

### 📊 Kafka Streaming

* Use **Apache Kafka** to scale the system for large cities with multiple manholes and real-time analytics.

---

## 💡 Applications

* 🏙️ Smart City Initiatives
* ⚠️ Urban Hazard Monitoring
* 🚧 Municipal Infrastructure Safety

---

## 📸 Visuals & Demo
<img width="263" height="542" alt="image" src="https://github.com/user-attachments/assets/405c527f-f123-4135-a46c-e5af99bb0d3f" />
<img width="1025" height="599" alt="image" src="https://github.com/user-attachments/assets/5e65a03c-1caf-471f-9b6e-b29683b90d16" />

---

## 📜 License

MIT License — feel free to use, modify, and improve this for your own city or university projects!

---

## ⭐️ Support

If you like this project or found it useful, leave a ⭐️ on the repo and help spread the idea.

---


---

### ✅ What You Can Do:
- Save this into your `README.md` file.
- Replace `"Your Name"` with your actual name.
- Add any images in a folder called `images` and link them accordingly in the visuals section.

Would you like me to convert this to a PDF or HTML landing page format next?
```
