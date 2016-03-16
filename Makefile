default:
	@echo "Choose a task"

server:
	lein ring server

compile:
	lein cljsbuild once dev

front:
	lein cljsbuild auto

production:
	lein cljsbuild once prod
