def pong_queue_port
  8080
end

def pong_queue_url(path = '')
  "http://localhost:#{pong_queue_port}#{path}"
end