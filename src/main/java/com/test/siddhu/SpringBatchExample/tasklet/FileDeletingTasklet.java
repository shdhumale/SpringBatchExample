package com.test.siddhu.SpringBatchExample.tasklet;

import java.io.File;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.UnexpectedJobExecutionException;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

public class FileDeletingTasklet implements Tasklet, InitializingBean {

	private Resource directory;


	public void afterPropertiesSet() throws Exception {
		Assert.notNull(directory, "directory must be set");
	}


	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

		File dir = directory.getFile();
		Assert.state(dir.isDirectory());

		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			boolean deleted = files[i].delete();
			if (!deleted) {
				throw new UnexpectedJobExecutionException("Could not delete file " + files[i].getPath());
			} else {
				System.out.println(files[i].getPath() + " is deleted!");
			}
		}
		return RepeatStatus.FINISHED;

	}

	public Resource getDirectory() {
		return directory;
	}

	public void setDirectory(Resource directory) {
		this.directory = directory;
	}

	/*public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}*/

}