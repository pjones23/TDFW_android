package app.tdfw;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
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
				// Play or stop the sound when the button is clicked.
				Log.i("Play Button", "Play button clicked.");
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
		if (mPlayer != null) {
			// release the media player when the sound is completed
			Log.i("TDFW sound", "Releasing the media player");
			mPlayer.release();
		}
		// finish the TDFW activity
		finish();
	}

	private void play() {
		// Create the media playing with the desired sound file
		
		if(mPlayer != null){
			if(mPlayer.isPlaying()){
				mPlayer.stop();
				/*
				 *  Only add the return statement if we want the button to act as a stop button
				 *  when the song is playing.
				 *  Currently, it will only be a play button.
				 *  The next version may use it.
				 */
				//return;
			}
			mPlayer.reset();
		}
		
		mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.td4w);

		mPlayer.setOnPreparedListener(new OnPreparedListener() {

			@Override
			public void onPrepared(MediaPlayer mp) {
				// Start playing the sound when the media player is prepared.
				System.out.println("Media player is prepared");
				mPlayer.start();

			}
		});

		mPlayer.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				Log.i("TDFW sound", "Playback completed.");
			}
		});

		mPlayer.setOnErrorListener(new OnErrorListener() {

			@Override
			public boolean onError(MediaPlayer mp, int what, int extra) {
				// Must reset the music player when an error occurs.
				mPlayer.reset();
				return false;
			}
		});

		// Setting the wake lock to keep the screen on while the music is
		// playing
		mPlayer.setWakeMode(getApplicationContext(),
				PowerManager.PARTIAL_WAKE_LOCK);

		mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

	}

}
