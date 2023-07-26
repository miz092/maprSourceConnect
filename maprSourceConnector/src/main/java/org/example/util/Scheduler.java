package org.example.util;

import org.example.service.MapRDBSourceTask;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Scheduler {


    private final MapRDBSourceTask mapRDBSourceTask;
    private final ScheduledExecutorService scheduler;

    public Scheduler(MapRDBSourceTask mapRDBSourceTask) {
        this.mapRDBSourceTask = mapRDBSourceTask;
        this.scheduler = Executors.newScheduledThreadPool(1);
    }

    public void startScheduler() {
        scheduler.scheduleAtFixedRate(this::pollScheduler, 0, 15, TimeUnit.MINUTES);
    }

    public void stopScheduler() {
        scheduler.shutdown();
    }

    private void pollScheduler() {
        try {
            System.out.println("fetched");
            mapRDBSourceTask.poll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
