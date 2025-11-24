package main;

import main.appflow.Appflow;
import org.jobrunr.configuration.JobRunr;
import org.jobrunr.storage.InMemoryStorageProvider;
import org.jobrunr.scheduling.BackgroundJob;
import org.jobrunr.scheduling.cron.Cron;

public class Main {

    public static void main(String[] args) {

        Appflow app = new Appflow();
        // Initialize JobRunr
        JobRunr.configure()
                .useStorageProvider(new InMemoryStorageProvider()) // No database needed
                .useBackgroundJobServer() // Starts the processing thread
                .useDashboard()           // Starts the dashboard at http://localhost:8000
                .initialize();

        BackgroundJob.scheduleRecurrently(
                "my-first-job",     // Optional id for this job
                Cron.every5minutes(), // A simple CRON expression
                () -> app.execute());
    }
}