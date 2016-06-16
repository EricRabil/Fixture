package com.ericrabil.fixture.database;

public interface IContext extends AutoCloseable{
	@Override
	public void close();
}
