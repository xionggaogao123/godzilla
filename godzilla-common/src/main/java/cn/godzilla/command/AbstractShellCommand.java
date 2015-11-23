package cn.godzilla.command;

import java.io.IOException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import cn.godzilla.common.Application;

public abstract class AbstractShellCommand extends Application implements Command{
	
	public Lock lock = new ReentrantLock();
	
	public Condition done = lock.newCondition();
	
	protected Process process = null;
	
	protected Runtime runtime = Runtime.getRuntime();
	
	public volatile boolean stop = false;
	
	public volatile boolean isSignaled = false;
	
	public void execute(String shellCommand, CommandEnum type) {
		
		try {
			process = runtime.exec(shellCommand);
			startdoLog(type);
			process.waitFor();
			lock.lock();
			if(!isSignaled) {
				done.await();
			}
			lock.unlock();
			stopdoLog();
			process.destroy();
		} catch(IOException | InterruptedException e) {
			e.printStackTrace();
			try {
				process.getErrorStream().close();
				process.getInputStream().close();
				process.getOutputStream().close();
			} catch(IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	protected abstract void startdoLog(CommandEnum type);
	
	protected void stopdoLog() {
		stop = true;
	}
	
}