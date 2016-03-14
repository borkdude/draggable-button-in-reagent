default:
	@echo "Choose a task"

server:
	lein ring server

compile:
	lein cljsbuild once

front:
	lein cljsbuild auto
