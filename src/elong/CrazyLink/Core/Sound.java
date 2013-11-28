package elong.CrazyLink.Core;


import java.util.HashMap;

import elong.CrazyLink.R;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class Sound {
	
	public enum E_SOUND
	{
		SLIDE,
		FILL,
		DISAPPEAR3,
		DISAPPEAR4,
		DISAPPEAR5,
		READYGO,
		TIMEOVER,
		SUPER,
		COOL,
		BOMB,
		MONSTER,
	}

	
	//��Ч������   
	int streamVolume;   
	  
	//����SoundPool ����   
	public SoundPool mSoundPool;    
	  
	//����HASH��   
	public HashMap<Integer, Integer> mSoundPoolMap;
	
	Context mContext;
	
	public Sound(Context c){

		mContext = c;
		InitSounds();
	}
	private void InitSounds()
	{
		System.out.println("InitSounds");
		initSounds();
	}
	

	private void initSounds() 
	{    
		//��ʼ��soundPool ����,��һ�������������ж��ٸ�������ͬʱ����,��2����������������,������������������Ʒ��   
		mSoundPool = new SoundPool(100, AudioManager.STREAM_MUSIC, 100);    
		  
		//��ʼ��HASH��   
		mSoundPoolMap = new HashMap<Integer, Integer>();    
		      
		//��������豸���豸����   
		AudioManager mgr = (AudioManager)mContext.getSystemService(Context.AUDIO_SERVICE);   
		streamVolume = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
		
		loadSfx(R.raw.s_readygo, E_SOUND.READYGO.ordinal());
		loadSfx(R.raw.s_timeover, E_SOUND.TIMEOVER.ordinal());
		loadSfx(R.raw.s_bomb, E_SOUND.BOMB.ordinal());
		loadSfx(R.raw.s_cool, E_SOUND.COOL.ordinal());
		loadSfx(R.raw.s_disappear3, E_SOUND.DISAPPEAR3.ordinal());
		loadSfx(R.raw.s_disappear4, E_SOUND.DISAPPEAR4.ordinal());
		loadSfx(R.raw.s_disappear5, E_SOUND.DISAPPEAR5.ordinal());
		loadSfx(R.raw.s_slide, E_SOUND.SLIDE.ordinal());
		loadSfx(R.raw.s_super, E_SOUND.SUPER.ordinal());
		loadSfx(R.raw.s_fill, E_SOUND.FILL.ordinal());
		loadSfx(R.raw.s_monster, E_SOUND.MONSTER.ordinal());
	}   
	     
	//������Ч��Դ  
	private void loadSfx(int raw, int id) {   
	   //����Դ�е���Ч���ص�ָ����ID(���ŵ�ʱ��Ͷ�Ӧ�����ID���ž�����)   
		mSoundPoolMap.put(id, mSoundPool.load(mContext, raw, id));    
	}       
	  
	//sound:Ҫ���ŵ���Ч��ID, loop:ѭ������  
	private void play(E_SOUND sound, int loop) {
		int id = sound.ordinal();
		mSoundPool.play(mSoundPoolMap.get(id), streamVolume, streamVolume, 1, loop, 1f);    
	}
	
	void play(E_SOUND sound)
	{
		play(sound, 0);
	}


}

