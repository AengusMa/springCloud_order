docker build -t test/redis .
docker run -d -p 6379:6379 --name test-redis test/redis

