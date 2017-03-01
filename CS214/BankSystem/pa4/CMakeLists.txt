cmake_minimum_required(VERSION 3.5)
project(asst4)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES client.c helper.c server.c session.c)
add_executable(asst4 ${SOURCE_FILES})