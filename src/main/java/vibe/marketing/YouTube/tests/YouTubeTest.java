package vibe.marketing.YouTube.tests;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import common.Priority;
import common.TestBase;

@Priority(18)
public class YouTubeTest extends YoutubeMethods{

	@Test (priority =1801)
	public void youTube_uploadVideoInYouTube() throws Exception{
		 nav2YouTube();
		 addNewVideo(youtubeTitle, youtubeDesc);
		 confirmationMessage("Video was successfully uploaded");
		 nav2YouTube();
		 verifyYouTube(youtubeTitle, youtubeDesc);
		}

	@Test (priority =1802)
	public void  youTube_updateTheVideoDetails() throws Exception{
		nav2YouTube();
		verifyYouTube(youtubeTitle, youtubeDesc);
		updateyoutube(youtubeTitle, youtubeTitleUpdated);		 
	}	

	@Test (priority =1804)
	public void  youTube_deleteVideo() throws Exception{
		nav2YouTube();		
		deleteYouTubeVedio(youtubeTitle);
		verifyNotToPresentUTube(youtubeTitle);
		
		 
	}
	
	@Test (priority =1805)
	public void  youTube_deleteAllVideos() throws Exception{
		nav2YouTube();
		deleteAllYouTubeVedios();
		
		 
	}
	

}
