/**********************************************************
 * 项目名称：山寨“爱消除”游戏7日教程
 * 作          者：郑敏新
 * 腾讯微博：SuperCube3D
 * 日          期：2013年11月
 * 声          明：版权所有   侵权必究
 * 本源代码供网友研究学习OpenGL ES开发Android应用用，
 * 请勿全部或部分用于商业用途
 ********************************************************/
package elong.CrazyLink.Core;

import android.os.Message;

public class Timer {
	
	long mMaxTime = 0;	//最大时间
	long mLeftTime = 0;	//剩下的时间
	long mUsedTime = 0;	//已经使用的时间
	long mStartTime;
	boolean mStop = true;
	
	
	public Timer(int maxTime)
	{
		mMaxTime = maxTime;
		mStop = true;
	}
	
	public void start()
	{
		mStartTime = System.currentTimeMillis()/1000 - mUsedTime;
		mStop = false;
	}
	
	public void pause()
	{
		mUsedTime = System.currentTimeMillis()/1000 - mStartTime;
		mStop = true;
	}
	
	public void resume()
	{
		mStartTime = System.currentTimeMillis()/1000 - mUsedTime;
		mStop = false;		
	}
	
	public int getLeftTime()
	{
		if(!mStop)
		{
			mUsedTime = System.currentTimeMillis()/1000 - mStartTime;
			mLeftTime = mMaxTime - mUsedTime;
			if(0 == mLeftTime)
			{
				mStop = true;
				Message msg = new Message();
				msg.what = ControlCenter.GAME_OVER;
			    ControlCenter.mHandler.sendMessage(msg);
			}

		}
		return (int)mLeftTime;
	}

}
