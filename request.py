import requests
import threading

success_people = []


def request():
    data = {
        "orderTotal": 2999,
        "streetName": "北京东城区",
        'tel': "18182818288218",
        'userId': "1",
        'userName': "Pushy"
    }
    headers = {
        'Content-Type': 'application/json;charset=UTF-8',
        'tname': threading.current_thread().getName()
    }
    resp = requests.post('http://localhost:8080/seckill/buy?seckillId={}'.format(1), json=data, headers=headers)
    print("[{}] {}".format(resp.status_code, resp.text))
    if resp.status_code == 200:
        success_people.append(threading.current_thread().getName())


def task():
    while (True):
        request()


if __name__ == '__main__':
    threads = []
    for i in range(100):
        t = threading.Thread(target=request, args=(), name="thread-{}".format(i))
        threads.append(t)

    for thread in threads:
        thread.start()
