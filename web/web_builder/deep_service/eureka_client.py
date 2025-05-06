import requests
import socket
import threading
import time

EUREKA_SERVER = "http://localhost:8761/eureka"
APP_NAME = "deep-service"
PORT = 9007

def get_ip():
    return socket.gethostbyname(socket.gethostname())

def register_with_eureka():
    instance_id = f"{get_ip()}:{APP_NAME}:{PORT}"
    data = {
        "instance": {
            "hostName": get_ip(),
            "app": APP_NAME.upper(),
            "vipAddress": APP_NAME,
            "secureVipAddress": APP_NAME,
            "ipAddr": get_ip(),
            "status": "UP",
            "port": {"$": PORT, "@enabled": "true"},
            "dataCenterInfo": {
                "@class": "com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo",
                "name": "MyOwn"
            },
            "instanceId": instance_id
        }
    }

    headers = {"Content-Type": "application/json"}
    url = f"{EUREKA_SERVER}/apps/{APP_NAME}"
    try:
        response = requests.post(url, json=data, headers=headers)
        print(f"Registered with Eureka: {response.status_code}")
    except Exception as e:
        print(f"Failed to register with Eureka: {e}")

def heartbeat():
    while True:
        try:
            instance_id = f"{get_ip()}:{APP_NAME}:{PORT}"
            url = f"{EUREKA_SERVER}/apps/{APP_NAME}/{instance_id}"
            res = requests.put(url)
            print(f"Heartbeat sent: {res.status_code}")
        except Exception as e:
            print(f"Heartbeat failed: {e}")
        time.sleep(30)  # every 30s
