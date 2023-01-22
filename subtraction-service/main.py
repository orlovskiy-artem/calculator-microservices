from functools import wraps
from datetime import datetime, timedelta
from flask import Flask, request
from time import time
import py_eureka_client.eureka_client as eureka_client

rest_port = 8084
eureka_client.init(eureka_server="http://discovery-service:8761/eureka",
    app_name="subtraction-service",
    instance_port=rest_port,
    renewal_interval_in_secs=1)
app = Flask(__name__)

REQUESTS_DATA = {}
def sliding_window(limit=3,seconds=10):
    def decorator(function):
        @wraps(function)
        def wrapper(*args,**kwargs):
            IP_address = request.remote_addr
            current_moment = datetime.utcnow()

            if IP_address in REQUESTS_DATA:
                last_request_time = REQUESTS_DATA[IP_address]['last_required_time']
                if current_moment - last_request_time <= timedelta(seconds=seconds):
                    REQUESTS_DATA[IP_address]['request_count'] += 1
                else:
                    REQUESTS_DATA[IP_address] = {'last_required_time':current_moment,
                                                 'request_count':1}
            else:
                REQUESTS_DATA[IP_address] = {'last_required_time':current_moment,
                                            'request_count':1}

            if REQUESTS_DATA[IP_address]['request_count'] > limit:
                return "Too many requests, please try again later.", 429

            return function(*args, **kwargs)
        return wrapper
    return decorator



@app.route('/subtract/<a>/<b>')
@sliding_window(3)
def subtract(a,b):
    return str(int(a) - int(b))

if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0',port=rest_port)
