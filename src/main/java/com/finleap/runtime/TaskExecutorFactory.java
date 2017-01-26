package com.finleap.runtime;

/**
 * Created by anlcan on 26/01/2017.
 *
 */
public abstract class TaskExecutorFactory <T extends StateExecutor> {
    /**
     * returns an Executor
     * @return executor for task
     */
   public abstract T executor();
}
