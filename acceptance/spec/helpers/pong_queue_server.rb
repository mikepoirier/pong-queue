require_relative './retry_for'

class PongQueueServer
  attr_reader :app_port

  def initialize(port)
    @app_port = port
  end

  def start

    return if app_is_running?

    unless artifact_exists?
      raise 'No Artifact Found, create with `./gradlew clean assemble` in project root directory.'
    end

    @some_app_pid = Process.spawn(
        "SERVER_PORT=#{app_port} java -jar build/libs/pong-queue.jar",
        chdir: '../',
        pgroup: true,
        out: 'pong-queue.std.log',
        err: 'pong-queue.err.log'
    )

    puts "Attempting to start the application at http://localhost:#{app_port}"
    retry_for(90) {
      resp = health_check
      raise 'Application never came up. Does `gradle dev` work?' unless resp.code == 200
    }
  end

  def stop
    return unless @some_app_pid
    puts 'Shutting app down'
    puts "Killing pid #{@some_app_pid}"
    Process.kill('TERM', @some_app_pid)
  end

  private
  attr_reader :some_app_pid

  def health_check
    HTTParty.get("http://localhost:#{app_port}/health")
  end

  def app_is_running?
    begin
      health_check.code == 200
    rescue
      false
    end
  end

  def artifact_exists?
    artifact_found = `[ -e ../build/libs/pong-queue.jar ] && echo true || echo false`
    return true if artifact_found == "true\n"
    false
  end
end