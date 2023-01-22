FROM python:3.8-slim-bullseye
WORKDIR /workdir/server
COPY . /workdir/server
RUN pip install -r requirements.txt
EXPOSE 8084
ENTRYPOINT ["python", "main.py"]
