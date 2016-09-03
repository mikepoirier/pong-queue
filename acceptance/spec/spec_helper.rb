require 'rubygems'
require 'bundler/setup'
require 'yaml'
require 'json'
require 'erb'
require 'rspec/retry'
require 'require_all'
require 'pp'

Bundler.require :default
require 'active_support'
require 'active_support/core_ext'

require_rel 'helpers'

some_app_spec_helper = PongQueueServer.new('8080')

RSpec.configure do |config|
  config.filter_run :focus
  config.run_all_when_everything_filtered = true
  config.default_formatter = 'doc'
  config.order = :random

  config.verbose_retry = true
  config.display_try_failure_messages = true
  config.example_status_persistence_file_path = 'examples.txt'

  config.before :suite do
    some_app_spec_helper.start
  end

  config.after :suite do
    some_app_spec_helper.stop
  end
end

def get(url)
  response = HTTParty.get(url)

  JSON.parse(
      response.body,
      symbolize_names: true)
end

def delete(url)
  HTTParty.delete(url)
end

def post(url, body)
  response = HTTParty.post(
    url,
    body: body.to_json,
    headers: {'Content-Type' => 'application/json'}
  )

  JSON.parse(
      response.body,
      symbolize_names: true)
end
