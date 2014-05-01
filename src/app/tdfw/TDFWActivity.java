package app.tdfw;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class TDFWActivity extends Activity {

	private ImageButton playImageBtn;
	private MediaPlayer mPlayer;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tdfw);
		System.out.println(getApplicationContext().toString());
		playImageBtn = (ImageButton) findViewById(R.id.playBtn);
		
		// Set the button
		playImageBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Handle sound file playback
				System.out.println("Testing on click");
				play();
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		//finish();
	}
	
	private void play() {
		// Create the media playing with the desired sound file
		mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.td4w);
		
		mPlayer.setOnPreparedListener(new OnPreparedListener() {
			
			@Override
			public void onPrepared(MediaPlayer mp) {
				// TODO Auto-generated method stub
				System.out.println("Media player is prepared");
				mPlayer.start();
				
			}
		});
		
		mPlayer.setOnCompletionListener(new OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer mp) {
				// TODO Auto-generated method stub
				System.out.println("Playback completed");
				if (mPlayer != null){
					System.out.println("Releasing media player");
					mPlayer.release();
				}
			}
		});
		
		mPlayer.setOnErrorListener(new OnErrorListener() {
			
			@Override
			public boolean onError(MediaPlayer mp, int what, int extra) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		
		// Setting the wake lock to keep the screen on while the music is playing
		mPlayer.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
		
		mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		
	}
	

}
