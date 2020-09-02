package com.test.siddhu.SpringBatchExample;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	public static void main(String[] args) {

		App obj = new App();
		obj.run();

	}

	
	private void run() {

		String[] springConfig = { "spring/batch/jobs/job-files.xml" };

		ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);
		//ApplicationContext context = new ClassPathXmlApplicationContext();

		JobLauncher jobLauncher = (JobLauncher) context.getBean("myJobLauncher");
		Job job = (Job) context.getBean("readMultipleFileJob");

		try {

			JobExecution execution = jobLauncher.run(job, new JobParameters());
			System.out.println("Exit Status : " + execution.getStatus());
			System.out.println("Exit Status : " + execution.getAllFailureExceptions());

		} catch (Exception e) {
			e.printStackTrace();

		}

		System.out.println("Done");

	}

}
