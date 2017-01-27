# AWS State Machine Interpreter


running `Hello World` example state machine
    
    mvn package
    java -jar target/aws-state-machine-jar-with-dependencies.jar hello_world.json {toot:4}

Each `Task` state is executed by one implementation of the `StateExecutor`. By default, the `PrintExecutor` just prints the state resource with the input it receives.