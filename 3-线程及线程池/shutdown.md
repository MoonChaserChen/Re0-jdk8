# shutdown
1. `ScheduledExecutorService` 在 `shutdown` 后会把线程池里 `submit` 的Task执行完；抛弃 `schedule` 且未执行的Task；
2. `ThreadPoolExecutor` 在 `shutdown` 后会把线程池里 `submit` 的Task执行完