#!/usr/bin/env ruby

require 'bundler/inline'

gemfile do
    source 'https://rubygems.org'
    gem 'rumouse'
end

mouse = RuMouse.new
time = Time.now

until 8 >= time.hour || time.hour <= 17 do
    starting_position = mouse.position
    puts "sleeping 1 minute"
    sleep 60
    if mouse.position != starting_position
        puts "mouse moved to position #{mouse.position}"
        puts "sleeping 30 seconds"
        sleep 30
    else
        puts "mouse not moving"
        puts "moving mouse for you"
        mouse.move mouse.position[:x], (mouse.position[:y]+100)
        sleep 0.1
        mouse.move mouse.position[:x], (mouse.position[:y]-100)
        puts "sleeping 15 minutes from current time: #{time.hour}:#{time.min}"
        sleep 15*60
    end
end


