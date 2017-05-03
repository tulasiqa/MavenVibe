
package common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class locators {
	
// Drivers details	
	public static String projectPath = System.getProperty("user.dir");
	public static String chrome_driver = projectPath + "\\browser_drivers\\chromedriver.exe";
	public static String ie_driver= projectPath + "\\browser_drivers\\IEDriverServer.exe";
	public static String safari_driver= projectPath + "\\browser_drivers\\SafariDriver.exe";

	public static String appUrl = "http://monat-stage.vibeoffice.com/";   // "http://idlife-stage2.vibeoffice.com/"  ; // "https://lpgn-prod.vibeoffice.com";   // "http://monat-stage.vibeoffice.com/";

	public static String uploadPhotoPath = projectPath + "\\testdata\\photos\\special-drinks.jpg";
	public static String uploadVideoPath = projectPath + "\\testdata\\videos\\special-drinks.jpg";
	public static String uploadDocPath = projectPath + "\\testdata\\docs\\special-drinks.jpg";
	
	public static String fileUploadMozilla = projectPath + "\\testdata\\auto-it\\fileUpload.exe";
	public static int DEFAULT_WAIT_TIME = 180;


	// Vibe Login Locators
	
	public static String inputName = "//*[@id='user_login']";		
	public static String inputPwd= "//*[@id='user_password']";
	
	// Vibe Login credentials

	public static String adminUser_text =   "g.khanuma" ;           //"stageadmin";  //"siva.kancherla";      //"kevin.mcevoy";  ///"gangadhar.hanuma1"; g.khanuma        //"viber.139";  //"siva.kancherla";               //"kevin.mcevoy";  
	public static String adminPwd_text  ="password";  //"P@ssword1"; //"password1";	
	public static String adminUser1_text = "kevin.mcevoy";            
	public static String adminPwd1_text =  "password1";    

	public static String monatUser = "1033";
	public static String monatPwd = "E8B2213";
	
	public static String nonadminUser_text = "automation.enroll1"; 
	public static String nonadminPwd_text = "password1";
	
	public static String nonadminUser1_text = "automation.enroll3"; 
	public static String nonadminPwd1_text = "password1";
	
	public static String nonadminUserTup_text = "ganeshlite1";
	public static String nonadminPwdTup_text = "password1";
	
	public static String distributorUser1_text = "raghavan.eyunni";
	public static String distributorUser1Pwd_text = "password1";
	
	public static String distributorUser2_text = "distributor.two";
	public static String distributorUser2Pwd_text = "password1";
	
	public static String shopUser = "tulasi.202";
	public static String shopPwd = "test123";
	
	public static String hotMailId_text ="pyrtest@hotmail.com"  ; //"icentristester@hotmail.com";
	public static String hotPwd_text ="ICt3st3r";     //"testing@123";
	
	public static String email = TestBase.getEmail(adminUser_text, appUrl);
	
		
	// Sign In link of the home page
	
	public static String lnkSignIn ="Sign In";						
  
	public static String btnLogin = "//*[@id='new_user']/div[1]/input";
    public static String homePgTitle_text = "VIBE - VIBEoffice.com";		// expected home page title 
    	
    public static String exphomepgTitle_text = "VIBE - your home officee";	// expected landing page title (after login)
    public static String distributorManagerLink = "Distributor Manager";
    public static String reportAbuseLink = "Reported Abuse";
    public static String reportAbuseList = "//*[@id='main-content']/table/tbody/tr/td[1]";
    
    
    public static String billingUser1_text = "billinguser1321";
	public static String billingPwd1_text = "password1";
	
	public static String billingUser2_text = "billinguser2";
	public static String billingPwd2_text = "password1";
	
	public static String billingUser3_text = "billinguser3";
	public static String billingPwd3_text = "password1";
	
	public static String billingUser4_text = "billinguser4";
	public static String billingPwd4_text = "password1";


 /* ****************** Calender locators **********************************************/
    
    public static String lnkBusiness = "Business";
    public static String lnkCalendar = "Calendar";
    public static String btnNewEvent = "//*[@id='calendar']/div[1]/div[3]/a";  // "div.fc-toolbar > div.fc-right > a.btn.btn-primary";     
    public static String btnAddEventFromList = "//*[@id='agenda-widget']/div[2]/div[2]/div/a[2]";
    public static String btnMoreOptions = "//*[@id='event_form']/div[4]/div/div/a[2]";
    public static String btnDeleteSingleInstance = "//*[@id='_frm_event_delete']/button[1]";
    public static String btnDeleteAllInstances = "//*[@id='_frm_event_delete']/button[2]";
    public static String tblCalendar = "#main-content > div > div > #calendar > div > div > table > tbody";  //> tr > td.fc-widget-content
    public static String btnList = "//*[@id='calendar']/div[1]/div[2]/button[4]";
    public static String eventsListOptions = "//*[@id='more_3_container']/button";
    public static String eventActions = "//*[@id='more_3_container']/ul/li";
    
    public static String txtShare = "Share";
    public static String txtEdit = "Edit";
    public static String txtDeleteEvent = "Delete Event";
    public static String eventsList = "//tr[contains(@id,'event')]";
    public static String eventUpdate = "Event is updated";
    // Create Event locators
                                           
    public static String inputEventName = "//*[@id='pyr_crm_event_title']";
    public static String inputEventStartDate = "//*[@id='pyr_crm_event_starts_at']";
    public static String inputEventEndDate = "//*[@id='pyr_crm_event_ends_at']";
    public static String iframeEventDesc = "#cke_pyr_crm_event_description > div > #cke_1_contents > iframe.cke_wysiwyg_frame.cke_reset";
    public static String eventDescription = "iframe > html > body.cke_editable cke_editable_themed cke_contents_ltr cke_show_borders";
    public static String inputEventDescription = "//*[@id='pyr_crm_event_description']";
    
    public static String dpdnEventType = "//*[@id='pyr_crm_event_event_type_id']";
    public static String inputEventLoc = "//*[@id='pyr_crm_event_location']";
    public static String inputEventAddr1 = "//*[@id='pyr_crm_event_address1']";
    public static String inputEventCity = "//*[@id='pyr_crm_event_city']";
    public static String inputEventPincode = "//*[@id='pyr_crm_event_postal_code']";
    public static String drpdnEventState = "//*[@id='pyr_crm_event_state']";
    public static String drpdnEventCtry = "//*[@id='pyr_crm_event_country']";
    public static String btnEventSave = "//*[@id='submit-form']";
    public static String eleMatchEvent = "//*[@class='fc-title']";
    public static String unsavedChanges = "//*[@id='dirty-modal']/div/div/h4";
    public static String inputEventTitle = "//*[@id='pyr_crm_event_title']";
    public static String btnBackEditEvent = "//*[@id='main-content']/div/div[1]/div[2]/a[1]";
    public static String calendarTitle = "//*[@id='calendar']/div[1]/div[1]";
    public static String eventTypes = "//*[@id='events_by_type_container']/button";
    
    
    // New Event testdata
    
    public static String newEventName_text = "New_Event" + TestBase.random();
    public static String invalidEventName = "Great";
    public static String newEventDesc_text = newEventName_text + " description.";
    public static String newEventStartdate_text = TestBase.getSystemDate();
    public static String newEventEnddate_text = TestBase.getSystemDate();
    public static String newEventType_text = "Meeting";
    public static String EventType2_text = "Products";
    public static String EventType3_text = "Presentation";
    public static String newEventName_updtext = newEventName_text + "_Updated";
    public static String pastEvent = "pastEvent " + TestBase.random();
    public static String futureEvent = "Future Event " + TestBase.random();
      
    public static String eventLoc_text = "CA";
    public static String eventAddr1_text = "Woods Street";
    public static String eventCity_text = "Sanjose";
    public static String eventPincode_text = "40201";
    public static String eventState_text = "Alaska";
    public static String eventCtry_text = "United States";
    

   // Repeat Event 
    
    public static String repeatEventName_text = "Repeat_Event_" + TestBase.random();
    public static String repeatEventName_text2 = "Repeat_Event " + TestBase.random();
    public static String repeatEventDesc_text = repeatEventName_text + " description.";
    public static String repeatEventStartdate_text = TestBase.getSystemDate();
    public static String repeatEventEnddate_text = TestBase.getSystemDate();
    public static String repeatEventType_text = "Meeting"; 	
    public static String repeatEventName_updtext = repeatEventName_text + "_Updated";
    
    public static String repeatEventLoc_text = "NJ";
    public static String repeatEventAddr_text = "Hudson Valley";
    public static String repeatEventCity_text = "NewYork";
    public static String repeatEventPincode_text = "43201";
    public static String repeatEventState_text = "New York";
    public static String repeatEventCtry_text = "United States";
    public static String btnEdit = "//*[@id='details']/div/div[2]/div[4]/a";
    public static String chkRepeatDaily = "//*[@id='pyr_crm_event_event_recurrence_attributes_period_daily']";
    public static String chkEndsAfter = "//*[@id='pyr_crm_event_event_recurrence_attributes_ends_type_2']";
    
  // Corporate Calendar Event 
    
    public static String corpEventName_text = "Corp_Event_" + TestBase.random();
    public static String corpEventDesc_text = corpEventName_text + " description.";
    public static String corpEventStartdate_text = TestBase.getSystemDate();
    public static String corpEventEnddate_text = TestBase.getSystemDate();
    public static String corpEventType_text = "Meeting"; 	
    public static String corpEventName_updtext = corpEventName_text + "_Updated";
    
    public static String corpEventLoc_text = "NJ";
    public static String corpEventAddr_text = "Hudson Valley";
    public static String corpEventCity_text = "NewYork";
    public static String corpEventPincode_text = "43201";
    public static String corpEventState_text = "New York";
    public static String corpEventCtry_text = "United States";    
    
   // Repeat Event dialog (For RepeatEvent type only)
    
    public static String radioRepEventDaily = "//*[@id='pyr_crm_event_event_recurrence_attributes_period_daily']";
    public static String radioRepEventDayOfWeek = "//*[@id='pyr_crm_event_event_recurrence_attributes_period_day_of_the_week']";
    public static String radioRepEventWeekly = "//*[@id='pyr_crm_event_event_recurrence_attributes_period_weekly']";
    public static String radioRepEventMonthly = "//*[@id='pyr_crm_event_event_recurrence_attributes_period_monthly']";
    public static String radioRepEventYearly = "//*[@id='pyr_crm_event_event_recurrence_attributes_period_yearly']";
    
    public static String radioRepEventNoEndDate = "//*[@id='pyr_crm_event_event_recurrence_attributes_ends_type_1']";
    public static String radioRepEventEndAfter = "//*[@id='pyr_crm_event_event_recurrence_attributes_ends_type_2']";
    public static String inputEventOccurance = "//*[@id='pyr_crm_event_event_recurrence_attributes_repeat']";
    public static String eventOccurance_text = "3";
    public static String radioRepEventOnDate = "//*[@id='pyr_crm_event_event_recurrence_attributes_ends_type_3']";    
    public static String inputRepEventDate = "//*[@id='pyr_crm_event_event_recurrence_attributes_ends']";
    public static String repEventOnDate_text = TestBase.getSystemDate();
    
    public static String inputRepeatEventEvery = "//*[@id='pyr_crm_event_event_recurrence_attributes_frequency']";
    public static String repeatEventEvery_text = "1";
    
    public static String btnRepEventSave = "//*[@id='_recur_save']";
    
    public static String pastEventName_text = "PastDated_Event_" + TestBase.random();
    public static String futEventName_text = "FutureDated_Event_" + TestBase.random();
    
 /* ******************  Vibe : Calender - Edit Event page ****************************************/
       
    public static String btnEditEvent = "//*[@id='eventEditLink']";  // div.modal-content > div.modal-body > div.modal-footer > a#eventEditLink.btn.btn-default
    public static String editEvent_text = "Vibe 1.6 Demo"; 
    
      
 /* ******************  Vibe : Calender - Delete Event page ****************************************/
       
    public static String btnDeleteEvent = "//*[@id='eventDeleteLink']";		// "#eventDeleteLink";
                                         
    
 /* ******************  Vibe : Calender - Allday Event page ****************************************/
    
    public static String chkAlldayEvent = "//*[@id='pyr_crm_event_all_day']";
    public static String chkRepeatEvent = "//*[@id='pyr_crm_event_repeat_field']";
    
 //  *******************  Vibe : Calender - Quick Event page ****************************************/
      
    public static String eleQuickEvent = "//*[@id='calendar']/div[2]/div/table/tbody/tr/td/div/div/div[5]/div[1]/table/tbody/tr/td[7]";
    
    public static String quickEventName_text = "Quick_Event_" + TestBase.random();
    public static String quickEventDescription_text = quickEventName_text + " description.";
    public static String quickEventStartdate_text = TestBase.getSystemDate();
    public static String quickEventEnddate_text = TestBase.getSystemDate();
    public static String eventStartTime = "//*[@id='pyr_crm_event_start_time_field']";
    public static String eventEndTime = "//*[@id='pyr_crm_event_end_time_field']";
    public static String quickEventName_updtext = quickEventName_text + "_Updated";
    public static String EventMeeting = "Meet to mate " + TestBase.random();
    public static String EventMeeting2 = "Go Get " + TestBase.random();
    public static String EventMeeting3 = "Test Event" + TestBase.random();
    public static String EventMeeting4 = "Test Event1" + TestBase.random();
    public static String EventMeeting5 = "Test Event" + TestBase.random();
    public static String upcomingEvent = "Upcoming Event " + TestBase.random();
    public static String anniversaryEvent = "Anniversary Event " + TestBase.random();
    public static String claBody = "#attendees > div:nth-child(1)";
    public static String claBodyText = "Your are cordially Invited "+ TestBase.random(); 
    public static String eventsubText = "Event subject "+TestBase.random();
    public static String btnBackEvent = "//*[@id='main-content']/div/div[1]/div[2]/a[1]";
    public static String btnCalendarListView = "//*[@id='calendar']/div[1]/div[2]/button[4]";
    public static String txtUpcomingEvents = "//*[@id='agenda-widget']/div[1]/div[2]/a";
    public static String drpdwnEventFilters = "//*[@id='filter_by_type_container']/button";
    public static String btnPrevEventsList = "//*[@id='agenda-widget']/div[2]/div[1]/div/div[1]";
    public static String btnNextEventsList = "//*[@id='agenda-widget']/div[2]/div[1]/div/div[3]";
    public static String corpEventsubText = "Corp Event subject "+TestBase.random();
    public static String corpEventMeeting  = "Corporate"+ TestBase.random();
    public static String corpListEvent  = "Corporate List"+ TestBase.random();
    public static String corpListEventProduct  = "Corporate Product "+ TestBase.random();

    
 
 /* ******************  Vibe : Calender - Create Event page ****************************************/
    
 
    // Create Event page fields
    
    public static String Event_text = "Vibe 1.5 Demo";
    public static String EventDescription_text = "Product Launch Event";
    public static String EventStartdate_text = TestBase.getSystemDate();
    public static String EventEnddate_text = TestBase.getSystemDate();
    public static String dpdnEventType_text = "Meeting"; 		// Meeting, Webinar, Presentation
    public static String inputEventLoc_text = "CA";
    public static String inputEventAddr1_text = "Woods Street";
    public static String inputEventCity1_text = "Sanjose";
    public static String inputEventPincode_text = "40201";
    public static String drpdnEventState_text = "Alaska";
    public static String drpdnEventCtry_text = "Mexico";
    
    public static String chkEventAllDay = "//*[@id='pyr_crm_event_all_day']";
    public static String chkEventRepeat = "//*[@id='pyr_crm_event_repeat_field']";
 
    public static String chkRepeatyEvent = "//*[@id='pyr_crm_event_repeat_field']";
    public static String inputEventCity1 = "//*[@id='pyr_crm_event_city']";
 
    
 /* ****************** Calender_NewEvent testcase  **********************************************/
    
    public static String newEvent_text = "New Event_" + TestBase.generateRandomNumberInRange(2, 200);
    public static String newEventDescription_text = "Product Launch Event_" +TestBase.generateRandomNumberInRange(2, 2000);
    public static String newEventLoc_text = "CA";
    public static String newEventAddr1_text = "Woods Street";
    public static String newEventCity_text = "Sanjose";
    public static String newEventPincode_text = "40201";
    public static String newEventState_text = "Alaska";
    public static String newEventCtry_text = "United States";
    
       
/* ******************  Vibe : Calender - Allday Event page ****************************************/
    
    public static String allDayEvent_text = "Allday Event_" + TestBase.generateRandomNumberInRange(3, 300);
    public static String allDayEventDescription_text = allDayEvent_text + " description.";
    public static String allDayEventName_text = "Allday_Event_" + TestBase.random();
    public static String allDayEventDesc_text = allDayEventName_text + " description.";
    
    public static String allDayEventStartdate_text = TestBase.getSystemDate();
    public static String allDayEventEnddate_text = TestBase.getSystemDate();
    public static String allDayEventType_text = "Meeting"; 		// Meeting, Webinar, Presentation
    public static String allDayEventLoc_text = "NJ";
    public static String allDayEventAddr_text = "Hudson Valley";
    public static String allDayEventCity_text = "NewYork";
    public static String allDayEventPincode_text = "43201";
    public static String allDayEventState_text = "New York";
    public static String allDayEventCtry_text = "United States";
    
    public static String allDayEventName_updtext = allDayEventName_text + "_Updated";
    
    
 /* ******************  Vibe : Calender - Allday Event page ****************************************/
    
    
    public static String AlldayEvent_text = "Allday Event demo";
    public static String AlldayEventDescription_text = "Allday Event demo";
    
    
    /* ******************  Vibe : Calender - Quick Event page ****************************************/
      
   
    public static String QuickEvent_text = "Quick Event demo";
    public static String QuickEventDescription_text = "Quick demo";
    public static String guest= "div.row.event_invitees > div > p";   //"#attendees > div:nth-child(1) > div:nth-child(2) > div > div.row.event_invitees > div > p";
    public static String guest1= "div.row.event_invitees > div:nth-child(";
    public static String guest2 = ") > p";
    public static String inviteguest2 = ") > div > a";
    public static String inviteBtn = "#invite-create-button";
    public static String titleInvite = "#label-for-invite-create-modal";
    public static String emailList = "#contact-lists > div.contact-list > div > div > div.media-body";
    public static String emailList1 = "#contact-lists > div.contact media row > div > div:nth-child(";
    public static String emailList2 = ") > div.media-body";
    public static String conList = "#invite-contacts > div > div > div > ul > li";
    public static String saveInv = "#invite_save";
    public static String subField = "input#pyr_crm_event_subject";
    public static String bodyText = "#pyr_crm_event_body";
    public static String finalSave = "#submit-form";
    public static String invCount = "#attendees > div:nth-child(1) > div:nth-child(2) > div > div.row.event_invitees > div:nth-child(3) > div > a";
    public static String invitees = "#view_invitees > div > div.modal-content > div.modal-body > ol > li > div > div >h4"; //"#view_invitees > div > div.modal-content > div.modal-body > ul";
    public static String invTitle = "#label-for-view_invitees";
    public static String send_Cal = "div#email_message_buttons > a.btn.btn-primary.btn-sm";
    public static String caltoolBar = "#calendar > div.fc-toolbar > div.fc-left > button";
    public static String filtCal = "input.style-scope.ic-filter";//"#agenda-widget > div.panel-heading > div.panel-tools > ic-fil";
    public static String items = "div > table > tbody > tr > td > a";
    public static String itemsB = "tr > td:nth-child(";
    public static String itemsA = ") > a";
    public static String deleteCal = "div.panel-tools > a.button.btn.btn-default";
    public static String eventTypeDots = "div#events_by_type_container > button > span.button-drop-down-display-text";
    public static String eventType = "#events_by_type_container > ul > li.dropdown-option > a";
    
    
    
    /* ******************  Vibe : Home page elements ****************************************/
    
    public static String lnkProfileDrpdn = "a#profile-dropdown.dropdown-toggle.profile_name > div > img";     // "//*[@id='profile-dropdown']";
    public static String lnkLogout = "//*[@id='logout']";
    public static String lnkGotoAdmin = "//*[@id='admin-header']/div/div/div[1]/a";
   
    /* ******************  Vibe : Corporate Calendar ****************************************/
       
    public static String lnkCorpCalendar = "//*[@id='collapse_CPOMVAGD']/div/ul/li[5]/a/span";
    public static String drpdnTimezone = "//*[@id='pyr_crm_event_in_timezone']";
    public static String eRankDefinitions = "//*[@id='event_form']/div[5]/div[1]/div[1]/div";
    public static String chkRankAll = "//*[@id='rank_all']";
    public static String chkRankConsultant = "//*[@id='pyr_crm_event_rank_definition_ids_1']";
    public static String eMarketLangs = "//*[@id='event_form']/div[5]/div[2]/div[1]/div";
    public static String chkMarketsAll = "//*[@id='market_all']";
    public static String chkMarketUS = "//*[@id='pyr_crm_event_market_language_ids_4']";
    public static String eSubscriptionplan = "//*[@id='event_form']/div[5]/div[3]/div[1]/div";
    public static String chkSubscriptionAll = "//*[@id='subscription_all']";
    public static String chkVibeLiteMonthly = "";
    
    public static String btnSave = "//*[@id='submit-form']";
    
    public static String CorpNewEvent_text = "Corporate Event demo";
    public static String CorpEventDescription_text = "Corporate Event Description demo";
    public static String CorpEventStartdate_text = TestBase.getSystemDate();
    public static String CorpEventEnddate_text = TestBase.getSystemDate();
    public static String CorpEventType_text = "Webinar"; 		// Meeting, Webinar, Presentation
    public static String CorpEventTimezone_text = "America/Chicago";
    
    public static String CorpEventLoc_text = "CA";
    public static String CorpEventAddr1_text = "Woods Street";
    public static String CorpEventCity1_text = "Sanjose";
    public static String CorpEventPincode_text = "40201";
    public static String CorpEventState_text = "Alaska";
    public static String CorpEventCtry_text = "Mexico";
    
 // Invite Guests 
    
    public static String inputEventQuery = "//*[@id='event_query']";
    public static String btnInviteGuests = "//*[@id='invite-create-button']"; //button#invite-create-button.btn.btn-default  ***
                                            
    //   public static String panelInviteGuests = "#invite-contacts > div > div > div > #contact-lists > div > div.content";
    //   public static String headerContactName = "#invite-contacts > div > div > div > #contact-lists > div > div.content > div > div > h4";
    
   // public static String btnSearch = "//*[@id='calendar']/div[1]/div[3]/div/div[2]/span/button";
    public static String inputSearchContact = "//*[@id='autocomplete']";
    public static String panelInviteGuests = "//*[@id='search-list']";
    
    public static String btnSaveGuests = "div > div.pull-right > button#invite_save.btn.btn-primary";
    
    
    public static String inputCalendarSubject = "//*[@id='pyr_crm_event_subject']";
    public static String inputSearchListView = "//*[@id='agenda-widget']/div[1]/div[2]/ic-filter/input";
    
    
  //  public static String inviteGuestFName_text = "jayadev";
  //  public static String inviteGuestLName_text = "mootha";
  //  public static String inviteGuestEMail_text = "vibe.icentris@gmail.com";
  //  public static String inviteGuestName = inviteGuestFName_text+" "+inviteGuestLName_text;
    
    public static String inviteGuestFName_text = "test";
    public static String inviteGuestLName_text = "contact";
  //  public static String inviteGuestEMail_text = "vibe.icentris@gmail.com";
    public static String inviteGuestName = inviteGuestFName_text+" "+inviteGuestLName_text;
    public static String inviteGuestEventSubject_text = "Inviting the Guest through Calendar Event.";
     
    // Calendar View
    
    public static String  calendarViewPane = "//*[@id='calendar']/div[1]/div[2]";
    public static String  calendarViewTable = "//*[@id='main-content']/div/div/div/div[1]/div[2]/table/tbody";
    					  
    // Day view
    	
    public static String  calendarDayViewPane = "//*[@id='calendar']/div[2]/div/table/tbody/tr/td/div[2]/div/div[4]/table/tbody/tr/td[2]/div";
    
 // Week view
    
    public static String calendarWeekViewPane = "//*[@id='calendar']/div[2]/div/table/tbody/tr/td/div[2]/div/div[4]/table/tbody/tr";
    
    
    public static String btnPrevMonth = "//*[@id='calendar']/div[1]/div[4]/button[1]";
    public static String btnNextMonth = "//*[@id='calendar']/div[1]/div[4]/button[2]";
    public static String btnBack2Calender = "div.btn-group > a.btn.btn-default";
    
    
    /* *********** Admin > Call Scripts  ************************************************************************/
    
    
    
    public static String inputCallScriptName = "//*[@id='pyr_crm_call_script_script_name']";
    public static String drpdnCallScriptStatus = "//*[@id='pyr_crm_call_script_status']";
    public static String divCallScriptMarketLang = "//*[@id='new_pyr_crm_call_script']/div[1]/div[2]/div[1]/div[1]/div";
    public static String inputMarketAll = "//*[@id='market_all']";
    public static String inputPublishedOn = "//*[@id='pyr_crm_call_script_publish_on']";
    public static String inputPublishedEndDate = "//*[@id='pyr_crm_call_script_end_date']";
    public static String divScriptContent = "cke_editable"; // "#cke_pyr_crm_call_script_script_content";
    public static String btnSubmitCallscript = "div > input#btnCallScriptSubmit"; // "//*[@id='btnCallScriptSubmit']";
       
    public static String callScriptName_text = "TestCallScript" + TestBase.random();
    public static String callScriptPendingName_text =  "PendingCallScript" + TestBase.random();
    public static String callScriptStatus_text = "Active";
   
    public static String publishedOn_text = "01/01/2016 12:00 PM";
    public static String publishedEndDate_text = "10/10/2020 12:00 PM";
    public static String tblCallScript = "#call_scripts_list";
    
    // Make a Call
    
    public static String drpdnPhoneScript = "#pyr_crm_make_a_call_phone_script";
    public static String textareaPhoneScriptNotes = "#pyr_crm_make_a_call_notes";
    public static String radioPhoneScriptInterest = "#pyr_crm_make_a_call_interest_very_interested";
    public static String btnPhoneScriptSave =  "div.modal-footer > button.btn.btn-primary";  // ".btn.btn-default.make_a_call_btn.btn.btn-primary"   //
    
    public static String phoneScriptNotes_text = callScriptName_text +  " notes.";
    
    public static String eleCallscriptCantBeBlank = "div.form-group.select.required.pyr_crm_make_a_call_phone_script.has-error > span.help-block";
  
     /* ***********Communications >>> Inbox  *************************/
    
    
    public static String subjectContent = TestBase.generateRandomNumberInRange(1, 200)+ " times, mail is automatically sent.";
	public static String subjectContent1 = TestBase.generateRandomNumberInRange(201, 500)+ " - mails,sent from automation Team.";
	public static String subjectContent2 = TestBase.generateRandomNumberInRange(4, 50)+ " - mb is file size";
	public static String invalidSearchText = "SorryNoConatacts"+TestBase.generateRandomNumberInRange(4, 5000);
	public static String inboxMsgBodyText = "Inbox message " + TestBase.generateRandomNumberInRange(4, 50);
   
    public static String inboxSearch="div > input#search_q";
    public static String inboxSearchIcon ="button#btn-search > i";
    public static String mailsPageDown ="#main-content > div > div.panel-heading > div.col-md-5 > div > div > span > button";
    public static String mailsPagedropDown ="#main-content > div > div.panel-heading > div.col-md-5 > div > div > span > div > a";
    public static String pagination1 = "#main-content > div > div.panel-heading > div.col-md-5 > div > div > span > div > a:nth-child(";
    public static String pagination2 = ")";
    
    public static String recipientsOne_text = "ravi@vibeofficestage.com" ;
    public static String recipientsTwo_text = "jayadev.mootha@branch-1-7.vibeoffice.com,jayadev@vibeofficestage.com" ;
    public static String recipientsGmail_text = "vibe.icentris@gmail.com" ;
    public static String composeBodySignatureText = "signature"+ TestBase.generateRandomString();
    public static String SignatureName = "signatureName"+ TestBase.generateRandomString();
    public static String  signLink  = "//*[@id='new_pyr_crm_message']/span[1]/div/div[1]/span[2]/div/a";
    public static String  manageSignatures = "//*[@id='new_pyr_crm_message']/span[1]/div/div[1]/span[2]/div/ul/li";
    /******************** Vibe : Community***********************************************************/
    
    public static String validateText = "can't be blank";
    public static String validateElement = "//*[@class='help-block']";
    public static String ddlVisibility = "//*[@id='pyr_community_post_visibility_setting_attributes_visibility_id']";
    public static String draftsTable = "//*[@id='user-activity-tabs']/div/div[1]/div[2]/div[1]/div[2]/article";
    public static String btnUpdateDraft = "//*[@id='submit_post']";
    public static String lnkBlog = "Blog";
    public static String communityStreamActivities = "//*[@id='widget-recent-activity']/div[1]/div[2]/div[@class='pyr_community_activity media']";
    public static String postInCommunityWall = "//*[@id='new-blog']/div[1]/h3";
    public static String btnClosePost = "//*[@id='page']/div[7]/div/div/div[1]/div/div[3]/div/div/button";
    public static String visibility_text_community = "COMMUNITY";
    public static String visibility_text_private = "PRIVATE";
    public static String visibility_text_public = "PUBLIC";
    public static String txtUpdatePost = "Post was successfully updated.";
    public static String widgetRecentActivity = "Recent Activity";
    public static String communityPageWidgetContainer  = "//*[@id='widget_manager_community']/div[2]/div/div[@class='row']";
    public static String communityPageDiv1Container = "//*[@id='widget_manager_community']/div[2]/div/div/div[1]/div";
    public static String communityPageDiv2Container =   "//*[@id='widget_manager_community']/div[2]/div/div/div[2]";
    public static String addPostTitle_text1 = "Vibe User Post_" + TestBase.generateRandomNumberInRange(2, 600);
    public static String btnSaveCoverPhoto = "//*[@id='updateProfileCoverPhotoModal']/div/div[1]/div[3]/button";
    public static String ValidatePhoto = "//*[contains(@id,'edit_pyr_core_profile')]/div[1]/ul/li";
    public static String txtValidateCoverPhoto = "Cover photo is invalid";
    public static String btnBrowseProfilePhoto = "#pyr_core_profile_avatar";
    public static String btnBrowseCoverPhoto = "#pyr_core_profile_cover_photo";
    public static String btnSaveProfilePhoto = "//*[@id='updateProfilePhotoModal']/div/div[1]/div[3]/button";
    public static String txtValidateProfilePhoto = "Avatar is invalid";
    public static String lnkViewAllFeaturedPhotos = "//*[contains(@id,'pyr_community_widgets_featured_photos')]/div/div/div[3]/a";
    public static String lnkViewAllFeaturedVideos = "//*[contains(@id,'pyr_community_widgets_featured_videos')]/div/div/div[3]/a";
    public static String lnkViewAllFeaturedBlogs = "//*[contains(@id,'pyr_community_widgets_featured_blogs')]/div/div/div[3]/a";
    public static String widgetFeaturedPhotos = "Featured Photos";
    public static String widgetFeaturedVideos = "Featured Videos";
    public static String widgetFeaturedBlogs = "Featured Blogs";
    public static String txtComment = "//*[@id='pyr_community_comment_content']";
    public static String commentBlogText = "Nice Blog";
    public static String btnRemoveImage = "Remove Image";
    public static String imageSrc = "//*[contains(@id,'edit_pyr_core_profile')]/div/div[1]/div[1]/div/img";//"//*[@id='page-header']/header/div/div[1]/div[1]/img";
    public static String coverPhoto = "//*[@id='page-header']/header";
    public static String coverPhotoSrc = "bg-contact-manager.jpg";
    public static String txtRemoveImage = "Missing avatar medium";
    public static String btnProfileSubmit = "//*[contains(@id,'edit_pyr_core_profile')]/input[10]";
    public static String btnRemoveCoverPhoto = "Remove Cover Photo";
    public static String viewAll = "//*[@id='user-activity-tabs']/div/div[2]/div[2]/div[3]/a";
    public static String viewAllVideos = "//*[@id='user-activity-tabs']/div/div[2]/div[3]/div[3]/a";
    public static String photoTag = "//*[@id='page']/div[7]/div/div/div[2]/div[2]/div[2]/div/section[3]/div[2]/a";
    public static String taggedPhotosList = "//*[@id='community-tabs']/div[1]/div[2]/div";
    public static String taggedVideosList = "//*[@id='community-tabs']/div[2]/div/div";
    
    /* ******************  Vibe : Community AddPhoto ************************************************/
    
    public static String eBrowse = "#pyr_community_photo_photo";
    public static String inputPhotoURL = "//*[@id='pyr_community_photo_remote_photo_url']";
    public static String inputPhotoTitle = "//*[@id='pyr_community_photo_title']";
    public static String inputPhotoDesc = "//*[@id='pyr_community_photo_description']";
    public static String inputPhotoTags = "//*[@id='pyr_community_photo_tag_list']";
    public static String drpdnVisibilitySettings = "//*[@id='pyr_community_photo_visibility_setting_attributes_visibility_id']";
    public static String chkPostOnMyWebsite = "//*[@id='share_in_pwp']";
    public static String btnSharePhoto = "//*[@id='new_pyr_community_photo']/div[7]/input";
    public static String commPhotoText = "Best Photo "+ TestBase.generateRandomNumberInRange(200, 500);;
    public static String PhotoTitle_text2 = "The best preparation for tomorrow is doing your best today "+ TestBase.generateRandomNumberInRange(1, 256);
    public static String PhotoTitle_text1 = "The beautiful season in " + TestBase.generateRandomNumberInRange(1, 256)+" days.";
    public static String PhotoTitle_text4 = "Good Days in " + TestBase.generateRandomNumberInRange(1, 256);
    public static String PhotoTitle_text5 = "My Tweet followers are " + TestBase.generateRandomNumberInRange(300, 1000);
    public static String PhotoTitle_text6 ="my website has "+ TestBase.generateRandomNumberInRange(300, 1000) + " links";
    public static String inputPhotoURL_text = "http://t3.gstatic.com/images?q=tbn:ANd9GcSkOqmGsSogAxDQm4qO2Pg1ovFst30B3RE0qDf6CbM-T3subziv1w";
    public static String inputPhotoTitle_text = "com Photo " + TestBase.generateRandomNumberInRange(1, 256);
    public static String inputPhotoTitle_text2 = "Test Photo " + TestBase.generateRandomNumberInRange(1, 256);
    public static String inputPhotoDesc_text = "The earth laughs in flowers.";
    public static String inputPhotoTags_text = "soft-Flowers";
    public static String photoURL_text = "http://t3.gstatic.com/images?q=tbn:ANd9GcSkOqmGsSogAxDQm4qO2Pg1ovFst30B3RE0qDf6CbM-T3subziv1w";
    public static String photoTitle_text = "Vibe Photo_" + TestBase.generateRandomNumberInRange(1, 256);
    public static String photoTitle_text2 = "Blog- Curiosity will conquer fear " + TestBase.generateRandomNumberInRange(1, 256) + " times";
    public static String photoDesc_text = "Healthy drinks from vibe";
    public static String photoTags_text = "coffee";
    public static String visibilitySettings_text = "COMMUNITY";
    public static String SharePhoto =  "div.actions > input.btn.btn-default";    
    public static String photosFrame = "#user-activity-tabs > div.panel.panel-full > div.panel-body > div > div > span";
     
    /* ******************  Vibe : Community AddVideo ************************************************/
    public static String drpdnProductCategory = "//*[@id='get_id']";  
    public static String lnkAddVideo =  "//*[@id='addPhotoModal']/div/div[1]/div[2]/div[1]/ul/li[2]/a";
    public static String inputVideoURL = "//*[@id='pyr_community_video_url']";
    public static String inputVideoTitle = "//*[@id='pyr_community_video_title']";
    public static String inputVideoDesc = "//*[@id='pyr_community_video_description']";
    public static String inputVideoTags = "//*[@id='pyr_community_video_tag_list']";
    public static String drpdnVisibilityVideoSettings = "#pyr_community_video_visibility_setting_attributes_visibility_id";
    public static String btnShareVideo = "//*[@id='new_pyr_community_video']/input[3]";
    public static String shareVideoButton = "#new_pyr_community_video > input.btn.btn-default";
    public static String inputVideoURL_text = "https://www.youtube.com/watch?v=dUDnku_wIbQ";
    public static String inputVideoTitle_text = "Vibe Promo Video" +  TestBase.generateRandomNumberInRange(1, 5000);
    public static String inputVideoDesc_text = "Vibe Promo" +TestBase.generateRandomNumberInRange(1, 5000);
    public static String inputVideoTags_text = "vibe_video_tag";
    public static String drpdnVisibilityVideoSettings_text = "COMMUNITY";
    
    public static String videoURL_text = "https://www.youtube.com/watch?v=dUDnku_wIbQ";

    public static String videoTitle_text = "Community Video_" + TestBase.random();
    public static String videoDesc_text = "Vibe Promo";
    public static String videoTags_text = "vibe_video_tag";
    public static String visibilityVideoSettings_text = "COMMUNITY";
     
    /* ******************  Vibe : Community ************************************************/
    
    public static String paneCommunityWall = "//*[@id='widget-recent-activity']/div"; //div[2] on branch
    public static String paneCommunityWallContent = "//*[@class='pyr_community_activity media']/div[@class='media-body']/div[@class='activity-content']/a";
    public static String paneCommunityWallStatus = "//*[@class='pyr_community_activity media']/div[@class='media-body']/div[@class='activity-content']";
    
    public static String paneFeaturedPhotos = "//*[@data-widget-path='widgets/featured_photos']/div/div/div[2]";	
    public static String paneFeaturedVideos = "//*[@class='pyr_community_activity media']/div/div/a";
    
    public static String inputCommunityStatusComment = "#pyr_community_comment_content";
    public static String communityStatusComment_text = "liked the comments";
    public static String btnCommunityStatusComment = ".new_pyr_community_comment > div.row-custom > div.input-group > span.input-group-btn > input.btn.btn-primary";
    
    public static String lnkCommunity ="Community";						
    public static String lnkSubCommunity = 	"//*[@id='nav_PPPKUYQZ_link']/span";					//"Community";						
    public static String lnkAddPhotoVideo ="Add Photo / Video";	
    public static String addPhotoButton = ".photo_modal.btn.btn-default";
    public static String addBlogButton = "#user-activity-tabs > div > div.col-md-8 > div > div.panel-heading > div.panel-tools > a.btn.btn-default";
    public static String btnUserSearch = "div.search.input-group > button.btn.btn-default";
    public static String lnkAddBlog ="Add Blog Post";
    public static String addNewvideo = "div#addPhotoModal > div > div.modal-content > div.modal-body > div.modal-tools > ul > li:nth-child(2) > a";
    public static String divAllBlogs = "//*[@class='pyr_community_activity media']/div[@class='media-body']/h4[@class='media-heading']";
    public static String divPhotos = "//*[@class='media-body']/h4[@class='media-heading']/a";
   

     /* ******************  Vibe : Community AddBlogPost ****************************************/
     
     public static String secondaryCommunityTab = "html/body/div[1]/section[1]/header/div[1]/div/nav/div/div[2]/ul[2]/li[4]/ul/li[1]/a/span";//"html/body/div[1]/section[1]/header/div[1]/div/nav/div/div[6]/ul/li[4]/ul/li[1]/a";
     public static String lnkAddBlogPost = ".padded10>a";
     public static String inputAddPostTitle = "#pyr_community_post_title";
     public static String inputAddPostExcerpt = "#pyr_community_post_excerpt";
     public static String drpdnAddPostVisibility = "#pyr_community_post_visibility_setting_attributes_visibility_id";
     public static String chkPublishPost = "#pyr_community_post_published";
     public static String btnCreatePost = "#submit_post";
     public static String okFirstTime = "div.panel-footer > button.btn.btn-success.pull-right";
    
     public static String inputAddPostTitle_text = "Vibe User Post";
     
     public static String drpdnAddPostVisibility_text = "COMMUNITY";
     public static String addPostTitle_text = "Vibe User Post_" + TestBase.generateRandomNumberInRange(2, 600);
     public static String addPostExcerpt_text = "Vibe User Excerpt";
  
     /* ******************  Vibe : Community AddBlogPost ****************************************/
     
     public static String inputCommunityStatus = "#pyr_community_status_status";

     public static String btnCommunityPost = "//*[@id='new_pyr_community_status']/div/div[2]/div[contains(text(),'Post')]" ;

     
  //   public static String btnCommunity2Post = "//*[@id='new_comment_form\*']/div/div/span/input";
     
     public static String communityStatus_text = "Comm Post " + TestBase.random();
     
     public static String inputWriteComment = "#pyr_community_comment_content";
     public static String communityPostComment_text = "Post comments on community";
          
     //************Community >>> MyProfile *************************/

     public static String COMMUNITY_Tab = "Community";
     //public static String COMMUNITY_Tab = "//*[@id='navbar-collapsable']/ul/li[4]/a/span[2]";
     public static String MYPROFILE ="My Profile";
     public static String activity ="//*[@id='user_activity_tabs_nav']/li[1]/a";
     public static String photos = "#user_activity_tabs_nav > li:nth-child(2) > a";
     public static String videos=  "#user_activity_tabs_nav > li:nth-child(3) > a";
     public static String blog =   "#user_activity_tabs_nav > li:nth-child(4) > a";
     public static String ACTIVITY_text = "Activity";
     public static String PHOTOS_text = "Photos";
     public static String VIDEOS_text = "Videos";
     public static String BLOG_text = "Blog";
     public static String CHANGECOVERPHOTO_text = "Change Cover Photo";
     public static String CHANGEPROFILEPHOTO_text = "Change Profile Photo";
     public static String updateUserbutton = "input.btn.btn-primary.pull-right";
     
     //public static String  addBlogPost="div > div.panel-body > div.padded10 > a:nth-child(2)";
     public static String  addBlogPost="Add Blog Post";
     public static String addPhotoVideo= "Add Photo";
     public static String changeCoverPhoto ="div.profile-update-cover-photo > a";
     public static String saveCoverPhoto ="div#updateProfileCoverPhotoModal > div > div.modal-content > div.modal-footer > button";
     public static String changeProfilePhoto = "//*[@id='page-header']/header/div/div[1]/div[2]/a";
     public static String profileUserName = "//*[@id='page-header']/header/div/div[2]";
     public static String postMsgs = "div.activity-content > a";
     public static String googAll = ".d-s.Cy.a1b.CTc.x0c";
     public static String googPostList = "div:nth-child(2) > div:nth-child(1) > a.d-s.ot-anchor";
     public static String googAccountIcon = ".gb_2a.gbii";
     public static String googLogout = "#gb_71";  
     
     

     //public static String changeProfilePhoto ="div.col-md-3.profpyrile-picture-container > div.profile-update-picture > a";
     
    //* ***********Community >>> MyProfile*************************/
     
     public static String confMsgPhoto = "Your photo has been added";
     public static String confMsgvideo = "Your video has been added";
     public static String confMsgPhotoDeleted  ="Photo is deleted";
     public static String confMsgVideoDeleted  ="Video is deleted";
     public static String confMsgCoverPhoto = "Photo cover updated";
     public static String confMsgProfilePhoto = "Profile photo updated";
    // public static String confMsgProfilePhoto = "Picture updated";
    // WebElement actualConfMsg = driver.findElement(By.xpath(confirmationMessage));
     public static String  title_addPost="pyr_community_post_title";
     public static String iframe = "iframe";
     public static String  body ="body"; // tagName
     public static String  excert="textarea#pyr_community_post_excerpt";
     public static String  visibility="select#pyr_community_post_visibility_setting_attributes_visibility_id";
     public static String  published="input#pyr_community_post_published";
     public static String  tags="input#pyr_community_post_tag_list";
     public static String  createPost="input#submit_post";
     public static String  visibilityDropdown="";
     public static String editProfile = ".panel-tools>a";
     public static String accountPanel = "//*[@id='main-content']/div/div[2]/ul";
     public static String accountPanelSize = "//*[@id='main-content']/div/div[2]/ul/li";
     public static String accountLinks1 = "html/body/div[1]/section[2]/div[2]/div/div/div/div/div[2]/ul/li[";
     public static String accountLinks2 ="]/a";
     public static String eleDrpdnProfile = "//*[@id='profile-dropdown']";
     public static String myPhotos = "div.photo-image > a > img";
    // public static String myPhotos = "//*[@id='user-activity-tabs']/div/div[2]/div[2]/div[1]/div[1]/a";
     public static String photoFrame = "//*[@id='user-activity-tabs']/div/div[2]/div[2]/div[2]";
     
    // public static String photoImage = "html/body/div[1]/section[2]/div[2]/div/div/div/div/div/div[2]/div[2]/div[2]/div[1]/div/a/img";
     public static String photoImage = "html/body/div[1]/section[2]/div[2]/div/div/div/div/div[1]/div[2]/div/div[1]/div/a/img";
     public static String photoDelete = ".//*[@id='page']/div[7]/div/div/div[2]/div[2]/div[2]/div/section[3]/div[1]/span[5]/a";
     public static String deleteOk = "html/body/div[5]/div/div/div[2]/button[2]";
     public static String confirmationMessage = "//*[@id='toast-container']/div/div";
     public static String myVideo = "//*[@id='user_activity_tabs_nav']/li[3]/a";
     //public static String myVideo = "#user_activity_tabs_nav > li:nth-child(3) > a";
    // public static String myVideo = "div#user-activity-tabs > div > div.col-md-5 > div.panel.panel-widget.my-videos > div.panel-heading > div.panel-title > a";
     //public static String videoImage = "div#user-activity-tabs > div > div.col-md-5 > div.panel.panel-widget.my-videos > div.panel-body > div:nth-child(1) > div > a > img";
     public static String videoImage = "//*[@id='user-activity-tabs']/div[1]/div[2]/div/div[1]/div/a/img";
     public static String feature  = "span[id^=featurephoto]";
     public static String like  = "span[id^=like_linkphoto]";
     public static String comment  = "span.like-comment-links > span:nth-child(2) > a";
     public static String share = "a[id^=share_item_Photo_]";
     public static String edit  = "div.community_activity_action.like-comment-links > span:nth-child(4) > a";
     public static String concat =">a";	
     public static String selection = "div.modal-sidebar-content > div > section:nth-child(3) > div > span";
     public static String reportTextArea = "div > div > div > textarea#pyr_community_abuse_report_abuse_reason.text.required.form-control";
     //public static String reportTextArea = "div.form-group.text.required.pyr_community_abuse_report_abuse_reason > textarea#pyr_community_abuse_report_abuse_reason";
     //.//*[@id='pyr_community_abuse_report_abuse_reason']";
     public static String reportButton = "div > div > input.btn.btn-primary";
     public static String commentTextArea = "#pyr_community_comment_content";
     public static String commentText = "Superb picture, Right now count is "+ TestBase.generateRandomNumberInRange(100,600);
     public static String btnPost = "div > div > span > input.btn.btn-primary";
     public static String profileDropdown = "#profile-dropdown";
     public static String customerProfileDropdown = ".dropdown-toggle.profile_name";
     public static String adminProfileDropdown = "#admin_tools_cms_options_container > button";
     public static String commentedText = "div.media-body > p";
     public static String delComment = "html/body/div[1]/section[2]/div[2]/div[2]/div/div/div[1]/div/div[3]/div/div[2]/div[2]/div[1]/div/div[4]/div[2]/div[1]/div[2]/span/a";
     public static String logOutAvon = "ul.dropdown-menu.right-menu > li > a";
     public static String logOut = "a#logout";
     public static String customerLogOut = "#navbar-user-toplevel > div > div.pull-right.user-nav > div > ul > li:nth-child(2) > a";
     
     
     public static String deleteFromDistMan = "div.community_activity_action.like-comment-links > span:nth-child(5) > a"; //"div.modal-sidebar > div.modal-sidebar-content > div > section:nth-child(3) > div.community_activity_action.like-comment-links > span > a [4]";
     public static String deleteOkFromDistMan = "div > div > div.modal-footer > button.btn.btn-primary";// "/html/body/div[7]/div/div/div[2]/button[2]";
     public static String mailId = "#messaging > div.tab-content.notification_preference > table > tbody > tr:nth-child(1) > td.email";
     public static String fbComm = "a.addthis_button_facebook.at300b > span";
     public static String gplsComm = "a.addthis_button_google_plusone_share.at300b > span";
     public static String lnComm = "a.addthis_button_linkedin.at300b > span";
     
     public  static  String btnCommunityProfileBrowse = "#pyr_core_profile_avatar";
     
    // /************Community >>> PEOPLE*************************/
     
     public static String peopleTab = "People";
     public static String search = "input#pyr_community_people_search_form_search_text";
     public static String btnSearchPeople = "//*[@id='new_pyr_community_people_search_form']/div/span/button";
     
     public static String searchIcon = "div.input-group > span.input-group-btn > button > i";// ".btn-group  >  .btn-group > #filterid" ; // branch-1.7.vibeoffice.com
     public static String filter = "div#filter_container > button > span:nth-child(1)";  
     public static String sort =  "div#sort_container > button > span:nth-child(1)";
     
     
     
     
     
     //" ul.dropdown-menu.filter-by > li "; 
     // branch-1.7.vibeoffice.com
     // "#filter_container > ul > li > a";	// master.vibeoffice
     
     public static String next = ".next > a";
     public static String sortDown=  "#sort_container > ul > li > a"; //".dropdown-menu.sort-by>li>a";
     public static String peopleFrame   ="div.media > div > h4 > a";
     public static String peopleList1 = "//*[@id='community-tabs']/div/div[2]/div/div[1]/div[";
     public static String peopleList2 = "]/div/div[2]/h4/a";
     public static String chatString ="How do you do!!!";
     //public static String chat = "span#profile-chat-button-container > a.chat-with-user";
     public static String chat = "//*[@id='profile-chat-button-container']/a";
     public static String inviteToChat ="#invite-to-chat > span#invite-to-chat-message";
     public static String blockUser = "div > div.media-right.follow-actions > a.block-user";
     public static String follow = "div > div.media-right.follow-actions > div >  a.follow-user";
     public static String unBlock = "div > div.media-right.follow-actions > a.unblock-user"; //.unblock-user";
     public static String unFollow = "div > div.media-right.follow-actions > a.unfollow-user";
     public static String inviteOkButton = "body > div.bootbox.modal.fade.bootbox-confirm.in > div > div > div.modal-footer > button.btn.btn-primary"; //"html/body/div[4]/div/div/div[2]/button[2]"; 
     public static String chatTextArea = "textarea[id^=new_message_]";
     public static String chatSend = "input#send-dock-chat-message";
     public static String chatClose ="div.chat-title > div.chat-tools > i";
     public static String lnkOfflineMessage = "div > div.media-right.follow-actions  > div >  span > a.chat-with-user";
     public static String poeplList  = "div.media-heading > a";
     public String taskEvent = "Products Demo with UID :"+TestBase.generateRandomString();
    public static String tasksAdd = "Tasks Add";
 	public static String tasksFuture = "Tasks Future" ;
 	public static String tasksIncomplete = "Tasks Incomplete";
 	public static String taskscomplete = "Tasks Completed";
 	public static String tasksNoDueDate = "Tasks No Due Date";
 	public static String tasksOverdue = "Tasks Overdue";
 	public static String tasksToday = "Tasks Today";
 	public static String taskIncompleteTitle = "//*[@id='tasks_incomplete']/div[1]/div[1]";
 	public static String taskcompleteTitle = "//*[@id='tasks_complete']/div[1]/div[1]";
 	public static String contactName = "Kevin";
 	public static String contactName2 = "Ganga";
 	public static String contactName3 = "Viber";
 	

// 	public String taskEvent = "Products Demo with UID :"+Functions.generateRandomString();

 	public String taskEvent3 = "Products Demo with UID : "+TestBase.generateRandomString();
 	public String taskEvent1 = "Especially task is created for you with TaskId_ "+TestBase.generateRandomString();
 	public String taskEvent4 = "Products Demo (past Date) with UID : "+TestBase.generateRandomString();
 	public String taskEvent5 = "No of Agreements are : "+TestBase.generateRandomString();
 	public String taskEvent6 = "This task is added from Goal whose order no is : "+TestBase.generateRandomString();
 	
 	public static String todaysTask_text = "TodaysTask_" + TestBase.random();
    public static String overdueTask_text = "OverdueTask_" + TestBase.random();
    public static String futureTask_text = "FutureTask_" + TestBase.random();
    public static String noDueDateTask_text = "NoDueDateTask_" + TestBase.random();
    
     public static String BUSINESS_tab = "Business";
     public static String TASKS_tab = "Tasks";
     public static String editWidgetsLink = "div > div > a.edit_widgets";  //"div#widget_manager_tasks > div.widget-manager-header > a";
     public static String editWidgetsLinkHome = "div#widget_manager_home.widget-manager > div.widget-manager-header > a";
     public static String editWidget ="div#widget_manager_tasks > div.widget-manager-header > div > div.btn-group > a:nth-child(1) > i";
     public static String closeWidget = "//*[@id='widget_manager_tasks']/div[1]/div/div[1]/a[4]/i";    //"div#widget_manager_tasks > div.widget-manager-header > div > div.btn-group > a.btn.btn-default > i.ic-icon-regular.ic-icon-close.";
     public static String searchWidget ="input#widgets_search_text";
    public static String widgetList = "//*[@id='widget_manager_tasks']/div[1]/div/div[2]/div/ul";
     //public static String widgetList ="div#widget_manager_tasks > div.widget-manager-header > div > div";
    public static String eventsWidget = "//*[@id='source_pyr_crm_widgets_events']/span"; 
    public static String dragWidgetToLocation = "//*[@id='widget_manager_home']";    // "//*[@id='main-content']";   
    public static String addEvent = "div#pyr_crm_widgets_events > div.events_list_index > div.panel.panel-widget > div.panel-heading > div.panel-tools > div > a:nth-child(2)";
    //public static String widgetsAreEnabled = "div#widget_manager_tasks > div.widget-manager-content.view_mode > div";
    public static String widgetsAreEnabled = "//*[@id='widget_manager_tasks']/div[2]/div/div";
  
    public static String tasksAddWidgets = "#source_pyr_core_widgets_tasks_add > span";
    public static String tasksCompletedWidgets ="#source_pyr_core_widgets_tasks_completed > span";
    public static String tasksCompletedIncompleteWidgets = "#source_pyr_core_widgets_tasks_completed_incomplete > span";
    public static String tasksFutureWidgets = "#source_pyr_core_widgets_tasks_future > span";
    public static String tasksIncompleteWidgets ="#source_pyr_core_widgets_tasks_incomplete > span";
    public static String tasksNoDueDateWidgets ="#source_pyr_core_widgets_tasks_no_due_date > span";
    public static String tasksCompletedAlternateWidgets = "#source_pyr_core_widgets_tasks_completed2 > span";
    public static String tasksInCompleteAlternateWidgets = "#source_pyr_core_widgets_tasks_incomplete2 > span";
    public static String tasksNoDueDateAlternateWidgets = "#source_pyr_core_widgets_tasks_no_due_date2 > span";
    public static String tasksOverdueAlternateWidgets = "#source_pyr_core_widgets_tasks_overdue2 > span";
    public static String tasksTodayAlternateWidgets = "#source_pyr_core_widgets_tasks_today2 > span";
    public static String tasksOverdueWidgets ="#source_pyr_core_widgets_tasks_overdue > span";
    public static String tasksTodayWidgets  ="#source_pyr_core_widgets_tasks_today > span";
    
    public static String companyNEWSWidgetsInMas = "//*[@id='source_pyr_core_widgets_company_news']/span"; 
    public static String companyNEWSWidgets= "//*[@id='source_pyr_core_widgets_company_news_main']/span";
    public static String companyAdsWidgets = "//*[@id='source_pyr_core_widgets_carousel_ads_main']/span";  //*[@id='source_pyr_core_widgets_carousel_ads_main']/span";//
    

    

    public static String addTask = "//*[@value='Add Task']"; // "#new_pyr_core_user_task > span > span > input";
    public static String addTask1 = "input.btn.btn-default.submit-button.btn.btn-default";
    public static String taskDescriptionInput = "//*[@id='pyr_core_user_task_task_title']";
    //public static String taskDescriptionInput = "input#pyr_core_user_task_task_title";
   
    public static String taskDescriptionText = "#new_pyr_core_user_task > div > div > label";
    public static String datePicker ="#new_pyr_core_user_task > span > div.form-group.date_picker."
    									+ "optional.pyr_core_user_task_due_date > div.input-group.date."
    									+ "date_picker > span > button > span";
    public static String datePicker1= "div.input-group.date.date_picker > span > button.btn.btn-default > span";
    public static String dateField = "input#pyr_core_user_task_due_date";
    public static String widgetsTitle = "div.panel-heading > div.panel-title";
    public static String taskCheckBox = "input[id^=checked_task_]";
    public static String taskLink = ".task-title>a";
    public static String deleteTasktoggle = ".btn.dropdown-toggle.btn-default";
    public static String deleteTask = ".dropdown-option>a";
    public static String deleteConfirmButton = "div > div > div.modal-footer > button.btn.btn-primary";
    public static String taskItems = "#task-detail-modal > div > div.modal-content > div.modal-body > div:nth-child(2) > div > div.panel-title > ul > li.btn-group.pull-right ";
    //public static String taskItems ="//*[@id='task-detail-modal']/div/div[1]/div[2]/div[2]/div/div[1]/ul/li";
    public static String editTask ="//*[@id='task-detail-modal']/div/div[1]/div[2]/div[2]/div/div[1]/ul/li[2]/a[1]";
   //public static String markComplete ="html/body/div[6]/div/div/div[1]/div[2]/div[2]/div/div[1]/ul/li[2]";
   public static String markComplete ="//*[@id='task-detail-modal']/div/div[1]/div[2]/div[2]/div/div[1]/ul/li[2]/label";
   //public static String markComplete = "div > div > ul.panel-nav > li.btn-group.pull-right > label.btn.btn-default";
   public static String deleteTaskbtn ="//*[@id='task-detail-modal']/div/div[1]/div[2]/div[2]/div/div[1]/ul/li[2]/a[2]"; 
    public static String confMsgTaskDescription = "Please enter a task description."; 
  //  public static String deleteWidgets = "div.widget-controls > span:nth-child(1) > i";
    public static String deleteWidgets_Old = "div.widget-controls > span.icon > i.fa.fa-trash-o";
    public static String deleteWidgets_New = "div.widget-controls > span.icon > i.ic-icon-regular.ic-icon-trash";
    public static String lnkcompletedTask = "div.panel-body > table > tbody > tr > td > a";  // "div.panel-body > table > tbody > tr > td:nth-child(1) > a";
    public static String lnkIncompletedTask = "tbody > tr > td > a";
    public static String deleteTaskDetailsbtn ="div > div.panel-title > ul > li.btn-group.pull-right > a:nth-child(4)";                 //"//*[@id='task-detail-modal']/vdiv/di[1]/div[2]/div[2]/div/div[1]/ul/li[2]/a[2]";//"#task-detail-modal > div > div.modal-content > div.modal-body > div:nth-child(2) > div > div.panel-title > ul > li.btn-group.pull-right > a:nth-child(4)";
    public static String TaksList = "div.panel-body";
    public static String taskName = "div.panel-body > table > tbody > tr >td > a";
   // public static String taskName1 = "div > table > tbody > tr > td.task-title > a";
    public static String taskName1 = "div > table > tbody > tr > td > a";
    public static String priority = "#pyr_core_user_task_priority";
    public static String updateTask = "div.new_but > input.btn.btn-default.btn.btn-primary";
    public static String saveTask = "#task-create-modal > div > div.modal-content > div.modal-footer > button";
    public static String sortTakeAction = "thead > tr > th.task-type.sorting";
    public static String sortPriority = "thead > tr > th.task-priority";
    public static String sortDueDate = "thead > tr > th.task-due.sorting";
    public static String viewConatct = "tbody > tr > td.task-type > a";
    public static String proceed = "div.row > a#nav_QXRTJDYW_link.dirty.proceed.btn.btn-default";
    public static String contactsNextStep = "#contact_manager > div.panel-content.col-md-8.no-gutter > div > div > div > div.cover > ul > li.active > a";
    
    
    // *************Reports***************//
    
    
    public static String REPORTS_tab= "#navbar-collapsable > ul > li:nth-child(5) > a > span.text";
   public static String reportsList = "#nav__link > span";
   public static String secondaryReport ="li > ul > li > a#nav_ANLHRHKY_link > span.text";
  // public static String secondaryReport = "Reports";
   //public static String secondaryReport = "html/body/div[1]/section[1]/header/div[1]/div/nav/div/div[6]/ul/li[6]/ul/li[1]/a";
  // public static String secondaryReport = "html/body/div[1]/section[1]/header/div[1]/div/nav/div/div[6]/ul/li[5]/ul/li[1]/a/span";
   public static String treeView ="#nav_XQLDGVYG_link > span";
   //public static String reportTableFrame = "div.col-md-12 > div#main-content.main-content-column > div > table > tbody > tr:nth-child(2) > td";
   public static String reportTableFrame = "//*[@id='main-content']/div/table/tbody/tr/td[1]";
   public static String rp1 = "//*[@id='main-content']/div/table/tbody/tr[";
   public static String rp2 = "]/td[1]/a";
   public static String exportToButton = "div > div.panel-heading > div.panel-tools > span > button" ;
   public static String exportTo_TEXT = "Export To";
   public static String filterOptionsButton = "#main-content > div > div.panel-heading > div.panel-tools > a > div";
   public static String filterOptions_TEXT = "Filter Options"; 
   public static String exportToDropDown = "div > div.panel-heading > div.panel-tools > span > ul > li";
   public static String noDownlineToShowResults = "div#run_report_form > div > ul > li";
   public static String noOrder = "div#main-content > div > table > tbody > tr:nth-child(2) > td:nth-child(1) > a";
   public static String commision ="div#main-content > div > table > tbody > tr:nth-child(3) > td:nth-child(1) > a";
   public static String volumeByperiod = "div#main-content > div > table > tbody > tr:nth-child(4) > td:nth-child(1) > a"; 
   public static String downlineSearch = "div#main-content > div > table > tbody > tr:nth-child(5) > td:nth-child(1) > a"; 
   public static String ordersReport = "div#main-content > div > table > tbody > tr:nth-child(6) > td:nth-child(1) > a"; 
   public static String generalReport = "div#main-content > div > table > tbody > tr:nth-child(7) > td:nth-child(1) > a"; 
   public static String anniversary = "div#main-content > div > table > tbody > tr:nth-child(8) > td:nth-child(1) > a"; 
   public static String birthDayReport = "div#main-content > div > table > tbody > tr:nth-child(9) > td:nth-child(1) > a"; 
   public static String titleNoOrder = "div#main-content > div > div.panel-heading > div.panel-title"; 
   public static String saveReport = "//*[@id='saved_reports_form']/a";
   public static String titleNoOrder_TEXT = "No Orders";
   public static String saveReport_TEXT = "Save Report";
   public static String runreport_TEXT = "Run Report";
   

   public static String secondaryReports1 = "div#main-content > div > table > tbody > tr:nth-child(";
   public static String secondaryReports2 = ") > td:nth-child(1) > a";
   public static String notImplimented = "body > div.dialog > h1";
   public static String noFilter = "span#results_table_container > section.report_results > div";
   public static String noFilterText="Display Distributors that placed an order last month but have not yet placed one this month."; 
   public static String notImplimentedText="We're sorry, but something went wrong.";
   public static String nofilterForOrdersText ="Display Distributor Orders for past month"; 
   public static String nofilterForAniversaryText = "Display Associates who have an anniversary in the business within the next 30 days."; 
   public static String nofilterForAniversaryText1= "Display Distributors who have an anniversary.";
   public static String nofilterForNewDistributors = "New Distributors Report";
   public static String nofilterForReportForUsersText = "This report is for users";
   public static String nofilterForVolumeByPeriodText = "Gets volume data by period";
   public static String nofilterForBirthDayText = "Display Distributors who have a birthday between given dates.";
   public static String nofilterForGeneralReportText = "This is General Report";
   public static String nofilterForDownlineSearchText = "Search for Distributors in your downline";
   public static String nofilterForSmokeTestText = "smoke test results";
   public static String nofilterForReportText = "No downline to show results";
   public static String nofilterForCommissionsReportText = "Accrued Commissions Report";
   public static String nofilterForHouseHoldCust = "List of Household Customers";
   public static String nofilterForCustomerSearch = "Search for Personally sponsored customers in your organization";
   public static String nofilterForNewAssociate = "New Associates within the selected period";
   public static String nofilterForFirstTimeSpr = "First Time Sponsor";
   public static String nofilterForAssociateSh = "Search for Associates in your organization";
   
   public static String exportBtn = "div.panel-tools > div > span > button.btn.btn-default.dropdown-toggle"; //"#main-content > div > div.panel-heading > div.panel-tools > span > button";
   public static String exportdp = "#report_dropdown_menu > li > a";
   public static String exDesc ="form > input#title";
   public static String exDueDate ="input#due_date";
   public static String exNotes="textarea#description";
   public static String exCreatask ="#create-bulk-task-modal > div > div.modal-content > div.modal-body > form > input.btn.btn-primary";
   public static String generalReportBody = "#collapse1 > section.constraints-section > div > div > label";
     //public static String generalReportBody = "div#main-content > div > div.panel-body";
   public static String genId ="#where_sign_55a34d657765626542300100";
   public static String genLastName ="#where_sign_55a34d697765626542330100";
   public static String genEmail ="#where_sign_55a34d6e77656264d3910000";
   public static String genAddress1 ="#where_sign_55a34d747765626542370100";
   public static String genCity ="#where_sign_55a34d7877656265423b0100";
   public static String genRank ="#where_sign_55a34d817765626542400100";
   public static String genFirstName ="#where_sign_55a34d677765626542310100";
   public static String genUserType ="#where_sign_55a34d6c7765626542350100";
   public static String genPhone ="#where_sign_55a34d7177656264d3930000";
   public static String genAddress2 ="#where_sign_55a34d7777656265423a0100";
   public static String genCountry ="#where_sign_55a34d7e77656265423e0100";
   public static String error404  = " div > h1";
   
   
   /***********       Shopping                ***********************************/
   
   public static String shopping_TAB ="Shopping";
   public static String placeOrder_TAB ="Place Order";
   public static String myAutoship_TAB = "My Scheduled Delivery"; //"My Autoship";  // My Scheduled Delivery
   public static String wishlist_TAB = "Wishlist";
   public static String orderHistory_TAB = "Order History";
   public static String orderHistoryItems =  "#orders > table > tbody > tr.order-history-items > td:nth-child(1) > div > div >a";//"#orders > table > tbody > tr.order-history-items > td:nth-child(1) > div > div > div > a";
   public static String orderHistoryTotal = "#orders > table > tbody > tr.ohistory > td:nth-child(4)";
   
   public static String selectCategory ="select#taxon";
   public static String selectCategoryOption ="select#taxon > option";
   public static String searchByName ="input#keywords";  
   public static String shopPanel = "div > nav#taxonomies > ul > li";
   public static String itemQuanity = "form > div > div > div > input#quantity";
   public static String addTocart = "div > button#add-to-cart-button";
   public static String totalItems = "div#products > div > div > div > a.info ";
   public static String ProdPrice = "div#products > div > div > div > span.price.selling.lead"; 
   public static String addCart_TEXT = "Add to Cart";
   public static String miniCartHeader = "#cart_dropdown > ul > li.dropdown-header";
   public static String  miniSubTotal ="#minicart-details > div:nth-child(2) > div:nth-child(1) > div.pull-right.order-total > strong";
   public static String  miniPV ="#minicart-details > div:nth-child(2) > div:nth-child(2) > div.pull-right.order-total";
   public static String  miniViewCart ="//*[@id='minicart-footer']/div[1]/a";  
   public static String  miniCheckOut ="div#minicart-details > div#minicart-footer > div > a#checkout-link";  
   public static String emptyCartConfirmation_TEXT = "Products deleted successfully";
   public static String todaysOrderheader_TEXT = "Today's Order Total";
   public static String noAutoship_TEXT = "No Autoship products found";
   public static String noWishlist_TEXT= "Your wishlist is empty";
   public static String todaysOrderheader = "#cart-adjustments > tbody > tr:nth-child(1) > td > div";
   public static String subTotal = "#cart-adjustments > tbody > tr:nth-child(2) > td:nth-child(2) > span";
   public static String PV = "#cart-adjustments > tbody > tr:nth-child(3) > td:nth-child(2) > span";
   public static String total = "#cart-adjustments > tbody > tr:nth-child(4) > td:nth-child(2) > span";
   public static String checkOut = "a#checkout-link";   
   public static String emptycart =" #clear_cart_link > input";
   public static String continueShopping ="#clear_cart_link > a";
   public static String nextInProducts = "div#current_products_in_autoship > div > a.btn.btn-primary";
   public static String nextShipping = "div.autoship-next > input.btn.btn-default.btn.btn-primary";
   public static String nextDelivary =   "//input[@value='Next']";   //"form > div.autoship-next > input";    //""; 
   public static String nextPayment = "#checkout_form_payment > div.autoship-next > input";
   public static String confirm = "input#place_order.btn.btn-primary";
   public static String radioAddress = ".//*[@id='shipping_address_entry_options']/label[2]";
   public static String productList = "div > div.panel-body.product-body > a";
   public static String keepAs = "//*[@id='use_same_address']";
   public static String keepSave = "div.modal-footer > button.btn.btn-primary";
   public static String checkOutOrder = "div > a.btn.btn-primary.checkout.col-md-12";
   public static String addAutoBtnMan = "#show_product_details_modal > div > div > div.modal-body > form > div:nth-child(3) > div:nth-child(2) > div:nth-child(3) > input[type='submit']:nth-child(4)";
   public static String taxon = "#s2id_autogen3";
   public static String details = "#sidebar > ul > li.active > a > span.text";   
   public static String fName= "input#order_ship_address_attributes_firstname";
   public static String lName= "#order_ship_address_attributes_lastname";
   public static String street1= "#order_ship_address_attributes_address1";
   public static String street2= "#order_ship_address_attributes_address2";
   public static String city= "#order_ship_address_attributes_city";
   public static String phone= "#order_ship_address_attributes_phone";
   public static String state= "select#order_ship_address_attributes_state_id";
   public static String stateOpt= "select#order_ship_address_attributes_state_id > option";
   public static String zipCode= "#order_ship_address_attributes_zipcode";
   public static String country = "#order_ship_address_attributes_country_id"; 
   
   public static String saveAndContinue= "#checkout_form_address > div.form-buttons > input";
   public static String saveAndContinueOfdelivary="#checkout_form_delivery > div.well.text-right.form-buttons > input";
   public static String saveAndContinueOfPayment="#checkout_form_payment > div.well.text-right.form-buttons > input";
   public static String alertMessage = "div > div.alert.alert-danger > ul > li";
   public static String noAutoship = ".panel-content.col-md-12";
   public static String createNewAutoship ="#main-content > div > div > div > div > div.col-md-3.pull-right.create_new_autoship > a";
   public static String contShopping = "#main-content > div > div.panel-body > div > a";
   public static String productsInAuto = "#current_products_in_autoship > table > tbody > tr > td";
   //public static String productsInAuto = "#current_products_in_autoship";
   public static String itemName = ".cart-item-description>h4>a";
   public static String placeOrder ="span.input-group-btn > input#place_order"; //"#checkout_form_confirm > div.text-right.form-buttons > input";
  
   public static String productIcon ="div > div.panel-body.product-body > a:nth-child(1) > img"; 
   public static String productimage = "#current_products_in_autoship > table > tbody > tr.line-item > td.cart-item-image > a > img";
   public static String prodSKU = ".shop-sku";
   public static String prodPV  = ".shop-pv";
   public static String prodBV = ".shop-bv";
   public static String prodYourPrice = ".your-price>td";
   public static String prodRetailPrice = ".r-price>td";
   public static String prodPrefCustomerPrice = ".pc-price>td";
   public static String prodConsultPrice = ".w-price>td";
   public static String priceDetails = ".price.selling.price.selling.table.table-responsive.table-striped.table-bordered";
   public static String shopsLink = "Shop";
   public static String ProductsLink = "Products";
   public static String addToAutoshipBtn = "button#add-to-autoship-button_on_page";
   public static String addToWishlistBtn = "a#icon_wishlist";
   public static String cartIcon = "div#cart_dropdown > a.dropdown-toggle > i.vibe-icon-shopping-cart > ::before";
   public static String facebook = "div.col-md-12.social-media.hidden-xs.hidden-sm > div >a.addthis_button_facebook.at300b > span";
   public static String googlePlus = "a.addthis_button_google_plusone_share.at300b > span";
   public static String twitter = " a.addthis_button_twitter.at300b > span ";
   public static String twitterIcon = " a.addthis_button_twitter.at300b > span > svg > g ";
   public static String linkedIn = "a.addthis_button_linkedin.at300b > span";
   public static String pwp = "#pwp_share > a > i";
   public static String shareByMail = "a#email_share > i";//"a#email_share > i.ic-icon-regular.ic-icon-envelope";
   public static String fbShareLink =  "__CONFIRM__"; //"#sharerDialogButtons > button._42ft._4jy0._4jy3._4jy1.selected._51sy";
   public static String fbLoginButton = "label#loginbutton > input#u_0_2";
   public static String fbEmail = "input#email.inputtext";       //"div#loginform > div.form_row.clearfix > input#email.inputtext";
   public static String fbPassword = "input#pass";   //"div#loginform > div.form_row > input#pass.inputpassword";
   public static String linkedShareaUpdate= "#share-text";
   public static String linkedShare = "div.submit-container > input";
   public static String linkedInEmail = "input#session_key-login";
   public static String linkedInPwd = "input#session_password-login.password";
   public static String linkedInLogin = "input#btn-primary.btn-primary";
   public static String linkedCloseWindow = "#footer > p:nth-child(1) > a";
   public static String closeWin = "#page > div.workspace-modal.modal.fade.in > div > div > div.modal-header > div > div:nth-child(3) > div > div >";
   public static String linkedEmail = "input#login-email";
   public static String linkedPwd = "input#login-password";   
   public static String googleAccounts = "#gaia_loginform";
   public static String googleEmail = "#Email";
   public static String googlePassword = "#Passwd";
   public static String googleMsg =  "div#\3a 0\2e f";
   public static String googleShare = "/html/body/div[1]/div[2]/div[2]/table/tbody/tr/td[1]/div[1]";// "body > div.ub-PNa > div.ub.zja.mh.jj > div.cc > table > tbody > tr > td.bI > div.d-k-l.b-c.b-c-Ba.qy.jt";
   public static String shippingNext = "span.input-group-btn > input#next_checkout_step.btn.btn-primary";
   public static String addNewAdd = "div.new_address > a.btn.btn-xs.btn-default.add-new-address-lnk";
   public static String fBEmail_text2 = "ramesh.buridi@icentris.com";
   public static String fBPwd_text2 = "Testing@123";
   public static String fBEmail_text = "pyrtest2013@gmail.com";
   public static String fBPwd_text = "ICt3st3r";
   
  
   
   
   
   /* ******************  Vibe : Business AddContact ****************************************/
   
   public static String inputContactFirstName = "#pyr_crm_contact_first_name";
   public static String inputContactLastName = "#pyr_crm_contact_last_name";
   public static String inputContactEmail = "#pyr_crm_contact_contact_emails_attributes_0_email";
   public static String eleAddMoreEmails = "//*[@id='new_pyr_crm_contact']/div[3]/div/p/a/span"; // .add_nested_fields>span
   public static String inputContactPhone = "#pyr_crm_contact_contact_phone_numbers_attributes_0_phone_number";
   public static String drpdnContactPhoneType = "#pyr_crm_contact_contact_phone_numbers_attributes_0_label";
   public static String eleAddMorePhoneNos = "//*[@id='new_pyr_crm_contact']/div[4]/div/p/a/span";
   public static String drpdnContactType = "#pyr_crm_contact_contact_type";  //*[@id='pyr_crm_contact_contact_type']";
   public static String drpdnContactInterestLevel = "#pyr_crm_contact_level_of_interest";
   public static String inputContactAddr1 = "#pyr_crm_contact_address1";
   public static String inputContactAddr2 = "#pyr_crm_contact_address2";
   public static String inputContactCity = "#pyr_crm_contact_city";
   public static String drpdnContactState = "#pyr_crm_contact_state";
   public static String inputContactPostalCode = "#pyr_crm_contact_postal_code";
   public static String drpdnContactCntry = "#pyr_crm_contact_country";
   public static String inputContactGenInfo = "#pyr_crm_contact_info";
   public static String btnUpdateContact = "div > div > input.btn.btn-default.btn-primary"; //.btn.btn-default.btn.btn-primary";
   
   public static String contactFirstName_text = "test";
   public static String contactLastName_text = "contact";
   public static String contactFirstName_text1 = "first";
   public static String contactLastName_text1 = "last";
   public static String contactEmail_text = "contact" + TestBase.generateRandomNumberInRange(1, 1000)+ "@icentris.com";
   public static String contactEmail_text1 = "contact1" + TestBase.generateRandomNumberInRange(1, 1000)+ "@icentris.com";
   public static String contactPhone_text = "4019283";
   public static String contactPhoneType_text = "Home";
   public static String contactType_text = "Unclassified";
   public static String contactInterestLevel_text = "Very Interested";
   public static String contactAddr1_text = "9202, LittleWoods, SA";
   public static String contactAddr2_text = "4920 HamptonAvenue, CA";
   public static String contactCity_text = "Sanjose";
   public static String contactState_text = "California";
   public static String contactPostalCode_text = "492910";
   public static String contactCntry_text = "United States";
   public static String contactGenInfo_text = "Adding business contacts";
   public static String lnkAllContacts = "//*[@id='list-results']/div[3]/a";
   
   public static String eleMatchContact = "//span[contains(@class,'visible-lg')]";
   public static String chkSelectContact = "#contacts_";		//*[@id='contacts_']
   public static String btnContactToggle = "#contact_detail > div > div > div > div > button.btn.btn-default.dropdown-toggle";
   public static String btnToggle = ".btn.btn-default.dropdown-toggle";
   public static String btnOk = "html/body/div[7]/div/div/div[2]/button[2]"; ///-------->
   public static String lnkCreateTaskFromContacts = "//*[@id='new-contact-task']/span[2]";

   // Contacts -> Send Messages
   
   public static String inputSendMessageSubject_text = "message from contacts page";  
   public static String btnSendMessage = "form#new_pyr_crm_message.simple_form.new_pyr_crm_message > div#email_message_buttons.page-controls > a.btn.btn-primary.btn-sm";
   
   // Contacts -> Set Appointment
   
   public static String inputAppointmentName = "input#pyr_crm_event_title.string.required.form-control";
   public static String appointmentName_text = "Appointment through Contacts";
   
   public static String inputAppointmentStartDate = TestBase.getSystemDate();
   public static String inputAppointmentEndDate = TestBase.getDate(2,"MM/dd/yyyy");
  
   /* ******************  Contacts -> Manage Groups *************************************** */
   //neelima
   public static String lstResources = "//*[@id='resources-modal']/div/div[1]/div[2]/div/div[2]/div[3]/div";
   public static String chkResAsset = "//*[contains(@id,'asset')]";
   public static String btnAttachAsset = "//*[@id='resources-modal']/div/div[1]/div[3]/button[1]";
   public static String lstAsset = "//*[@id='new_pyr_crm_message']/div[@class='attachment']";
   //neel
   public static String tblGroups = "//*[@id='listContactGroups']/tbody";
   
   public static String inputGroupName = "//*[@id='pyr_crm_contact_category_category_name']";
   public static String btnCreateGroup = ".btn.btn-primary.btn-sm.pull-right";
   public static String btnCloseGroup = "div.modal-header > button.close > i.ic-icon-regular.ic-icon-close::before";        // ".close"; //div.modal-header > button.close > i.vibe-icon-close
   
   public static String panelManageGroup = "#listContactGroups>tbody";    // "html/body/div[9]/div/div[1]/div[2]/div/div[1]/form/table";
   // public static String verifyGroupNameText = "//*[@id='listContactGroups']/tbody/tr/td[1]";
   
   public static String groupName_text = "TestGroup_" + TestBase.random();
   
   // Add Contacts 2 Groups

   public static String panelGroups = "//*[@class='content']/ul";
   
   public static String panelContacts = "//div[@class='add_group_contacts']";
   public static String btnSavenClose = "//input[@value='Save And Close']";
   public static String contFName_text = "ContUser"+TestBase.generateRandomNumberInRange(1, 1000);
   public static String contLName_text = "Tester";
   public static String contEmail_text = "contuser" + TestBase.generateRandomNumberInRange(1, 1000)+ "@icentris.com";
   public static String contEmail_text1 = "mailer" + TestBase.generateRandomNumberInRange(1, 1000)+ "@icentris.com";
   public static String ContLink ="#home-link";
   public static String manageGrpLink= ".//*[@id='main-panel']/div[2]/div[2]/div";
   public static String groupList = "div#group-manager-list > div > div:nth-child(1)";
   public static String socialList = "#contact-sources > div > a > div:nth-child(2)";
   public static String gmailText= "html > body";
   public static String YahooMailId ="pyr_test@yahoo.com" ;   //"automater16";
   public static String YahooPWD = "ICt3st3r";      //"password@123";
   public static String yaEmail= "//*[@id='login-username']";
   public static String yaNext= ".//*[@id='login-signin']";
   public static String yaPwd= ".//*[@id='login-passwd']";
   public static String yahooAlert = "html/body/font/b";
   public static String yahooAgree = "input#xagree.pure-button.pure-button-primary";
   
   //public static String yaEmail= "//*[@id='login-username']";
   public static String mailAlert = "div.alert.alert-danger > ul > li";
   public static String hotEmail= "input#i0116";
   public static String hotPwd= "input#i0118";
   public static String hotActText= "html>body";
   public static String hotSign = "input#idSIButton9.btn.btn-block.btn-primary.btn-image.btn-image-svg";
   public static String contSearch =  "//*[@id='contact_query']";
   public static String grpSearch =  "//*[@id='group_query']";
   public static String searchReslt =  "div.alert.alert-danger";
   public static String advSerch =  ".btn.btn-primary.btn-inspect";  //   ".btn.btn-default.btn.btn-primary.btn-inspect";
                                    
   public static String contFName = "input#contact_first_name";
   public static String addtoGrp = "Add To Group"; 
   
   /* ******************  Vibe : Business EditContact ****************************************/
   
   public static String btnContactEdit = "#contact_detail > div > div.panel-heading > div.panel-tools > div > a:nth-child(1)";
   //public static String btnContactEdit = "//*[@id='contact_detail']/div/div[1]/div[2]/div/a[1]";
   public static String btnContactSendMsg = "//*[@id='contact_detail']/div/div[1]/div[2]/div/a[2]";
   public static String btnContactSetAppointment = "//*[@id='contact_detail']/div/div[1]/div[2]/div/a[3]";
   public static String btnContactCreateTask = "//*[@id='contact_detail']/div/div[1]/div[2]/div/a[4]";
   
   public static String eleContactHistory = "//*[@id='contact_manager']/div[2]/div/div/div/div[1]/ul/li[4]/a";

   public static String inputContactSearch = "#contact_query";
   public static String btnContactSearch = "//*[@id='main-content']/div/div[1]/div/div[1]/form/div/span[2]/button";
   public static String contactschkbox = "div > div > input#contacts_";
   public static String contactsName = "div > div > h4.media-heading.primary-text > span.visible-lg";
   
   
   public static String eleContactMatchHistory = ".col-md-10>label";
   public static String contactNewPostalCode_text = "999999";
   
   public static String eleContactDelete = "//*[@id='contact_detail']/div/div[1]/div[2]/div/ul/li[2]/a";
   
   
   public static String createTask = "#contact_detail > div > div.panel-heading > div.panel-tools > div > a:nth-child(4)";
   
    
   // Add Notes
   
   public static String contactNotes = ".cover > .nav.nav-tabs>li:nth-child(3) > a";
   public static String contactCreateNote = "#contact-notes-widget > div > div.panel-tools > div.btn-group > a.btn.btn-default:nth-child(1)";
   public static String contactSendMessage = "div.panel-tools > div.btn-group > a.btn.btn-default:nth-child(2)";
   public static String contactSetAppointment = "div.panel-tools > div.btn-group > a.btn.btn-default:nth-child(3)";
   public static String contactCreateTask = "div.panel-tools > div.btn-group > a.btn.btn-default:nth-child(4)";
   public static String addContactNotes = "#note_toggle > #new_note_form > div > textarea#pyr_core_note_content.text.required.form-control";
   public static String contactTaskDesc_text = "Create task from contact";
   public static String addContactNotes_text = "Contact_Notes_" + TestBase.random(); 
   public static String contactTaskSave = "div.modal-footer > button.btn.btn-primary";
   public static String drpdnTaskPriority = "//*[@id='pyr_core_user_task_priority']";
   public static String taskPriority_text = "High";
   public static String tblHistory = "//*[@id='history']/div/div[2]";
      
   
  // Contacts -> Add Notes
   
   public static String lnkDetails = ".cover > .nav.nav-tabs>li:nth-child(1) > a";
  // public static String lnkNotes = ".cover > .nav.nav-tabs>li:nth-child(3) > a";
   public static String lnkCreateNote = "div.panel-tools > div.btn-group > a.btn.btn-default:nth-child(1)";
  // public static String tblContactNotes = "//*[@id='contact-notes-widget']/div[2]/table/tbody";
   
   /* *************  Settings ****************************************/
   
   public static String inputProfileMyStory = "//*[@id='pyr_core_profile_story']";
   public static String profileMyStory_text = "iCentris Company Profile";
   public static String profileTable = "html/body/div[1]/section[2]/div[2]/div/div/div/div/div[3]/div/div[2]/div/div[1]/form/section[1]/div/div/table";
   
   public static String profileTableCol = "html/body/div[1]/section[2]/div[2]/div/div/div/div/div[3]/div/div[2]/div/div[1]/form/section[1]/div/div/table/tbody/tr[1]/td";
   
   public static String radioCommunityProfileEmail = "html/body/div[1]/section[2]/div[2]/div/div/div/div/div[3]/div/div[2]/div/div[1]/form/section[1]/div/div/table/tbody/tr[3]/td[4]/div/span[1]/input";
   public static String radioCommunityProfilePhone = "html/body/div[1]/section[2]/div[2]/div/div/div/div/div[3]/div/div[2]/div/div[1]/form/section[1]/div/div/table/tbody/tr[4]/td[4]/div/span[1]/input";
   public static String radioCommunityProfileFax = "html/body/div[1]/section[2]/div[2]/div/div/div/div/div[3]/div/div[2]/div/div[1]/form/section[1]/div/div/table/tbody/tr[5]/td[4]/div/span[1]/input";
   public static String radioCommunityProfileCell = "html/body/div[1]/section[2]/div[2]/div/div/div/div/div[3]/div/div[2]/div/div[1]/form/section[1]/div/div/table/tbody/tr[6]/td[4]/div/span[1]/input";
 	
   // edit first name in community profile
   public static String btnCommunityFNameEdit = "html/body/div[1]/section[2]/div[2]/div/div/div/div/div[3]/div/div[2]/div/div[1]/form/section[1]/div/div/table/tbody/tr[1]/td[2]/a";
   public static String inputCommunityFName = "#pyr_core_profile_first_name";
   public static String communityFName_text = "test";
   public static String btnCommunityFNameSave = "//*[@id='first_nameFieldModal']/div/div[1]/div[3]/button";
   
   // community profile web table locators
   public static String p1 = "html/body/div[1]/section[2]/div[2]/div/div/div/div/div[3]/div/div[2]/div/div[1]/form/section[1]/div/div/table/tbody/tr[";
   public static String p2 = "]/td[" ;
   public static String p3 = "]";
   
   public static String inputCommunityFbook = "#pyr_core_profile_facebook";
   public static String inputCommunityTwittwer = "#pyr_core_profile_twitter";
   public static String inputCommunityLinkedin = "#pyr_core_profile_linkedin";
   public static String inputCommunityGooglePlus = "#pyr_core_profile_google_plus";
   public static String inputCommunityYoutube = "#pyr_core_profile_youtube";
   public static String inputCommunityHometown = "#pyr_core_profile_hometown";
   public static String inputCommunityGender = "#pyr_core_profile_gender";
   
   public static String communityFbook_text = "https://www.facebook.com/pyr.testuser";
   public static String communityTwittwer_text = "https://twitter.com/pyrtest";
   
   public static String btnCommunityUpdate = "//form[contains(@id,'edit_pyr_core_profile')]/input[@id='submit']";
   
   public static String communityPageHeader = "//*[@id='page-header']/header/div/div[1]";
   
   /* *************  Vibe : Profile dropdown -> Linked Accounts page  *********************************/
    
   public static String paneLinkedAccounts = ".panel-content.col-md-10";
   
   
   /* *************  Vibe : Profile dropdown -> Language Settings page  *********************************/
     
   public static String inputLanguageMarket = "#pyr_core_language_settings_form_market";
   public static String drpdnLanguage = "#pyr_core_language_settings_form_language";
   public static String drpdnLanguageTimezone = "#pyr_core_language_settings_form_timezone";
   public static String btnLanuageSave = "//*[@id='new_pyr_core_language_settings_form']/div[4]/div/input";
   
   public static String language_text = "English";
   public static String languageTimezone_text = "America/New_York";
   
   /* *************  Vibe : Profile dropdown -> Security page  *********************************/
   
   public static String inputCurrentPwd = "#password_edit_current_password";
   public static String inputNewPwd = "#password_edit_new_password";
   public static String inputConfirmPwd = "#password_edit_confirm_password";
  // public static String btnSecuritySave = "//*[@id='edit_user_94']/div[4]/input";
   public static String  btnSecuritySave = "//*[contains(@id,'edit_user')]/div[2]/div[4]/input";
   
   public static String securityPanel = ".panel-content.col-md-10";
   public static String headerUserName = "#edit_user_32>h4";
   
   public static String newPassword_text = "password1";
   
 /* *************  Vibe : Marketing -> ResourceLibrary page  *********************************/
   
   public static String productCategoryList = "//*[@id='categories_list']/li";   // #categories_list; 
   public static String editProductCategory = ".fa.fa-pencil-square-o";
   public static String deleteProductCategory = ".fa.fa-trash-o";
  
   public static String  prodcategory_inputbefore = "html/body/div[1]/section/div[2]/div/div/div/div/div/form/div/div[1]/div[9]/div[2]/div/table/tbody/tr[";
   public static String  prodcategory_inputafter = "]/td/input";
   
   
   // Add / Edit Category
  
   public static String  inputAddNewCategory = "#pyr_core_resource_category_category_name";
   public static String  drpdnParentCategory = " #pyr_core_resource_category_parent_category_id";
   public static String  chkMarketAll = "div#category_markets > input#market_all";
   public static String  btnCreateResourceCategory = "div.actions > input.btn.btn-default";
  
  
   
   // Resource Library -> Add New Resource page
  
   public static String inputNewResourceTitle = "input#pyr_core_resource_title";
   public static String inputNewResourceDescription = "textarea#pyr_core_resource_description";
   public static String drpdnNewResourceFileType = "#pyr_core_resource_file_type";
   public static String btnNewResourceFileBrowse = "#pyr_core_resource_file_path";
   public static String tblNewResourceCategoryList = "//*[@id='categories_list']/li";
   public static String resourceRowsList = "//*[@id='index_page']/div[@class='row']";
   public static String btnMoveSelectedResource = "//*[@id='main-content']/div/div[1]/div[2]/a[3]";
   public static String lstCategories = "//*[@class='modal-body']/div/div[2]/ul/li";
   public static String btnMove = "//*[@id='selected-resources-modal']/div/div[1]/div[3]/a";
   public static String btnNewResourceIconBrowse = "#pyr_core_resource_icon_path";
   public static String btnNewResourceTaglist = "#pyr_core_resource_tag_list";
   public static String drpdnNewResourceStatus = "#pyr_core_resource_status";
   public static String inputPublishDate = "//*[@id='pyr_core_resource_publish_date']";
   public static String publishDate_text = TestBase.getDate(2,"MM/dd/yyyy");
   public static String btnCreateResource = "div.col-md-8 > div > input.btn.btn-primary";
   public static String newResourceTitle_text  = "Bloosom Mugs"+TestBase.generateRandomNumberInRange(2, 900);
   public static String newResourceTitle_text1 = "Bloosom Mugs1"+TestBase.generateRandomNumberInRange(2, 900);
   public static String newResourceTitle_text2 = "Bloosom Mugs2"+TestBase.generateRandomNumberInRange(2, 900);
   public static String newResourceTitle_text3 = "Bloosom Mugs3"+TestBase.generateRandomNumberInRange(2, 900);
   public static String newResourceTitle_text4 = "Bloosom Mugs4"+TestBase.generateRandomNumberInRange(2, 900);
   public static String newResourceTitle_text5 = "Bloosom Mugs5"+TestBase.generateRandomNumberInRange(2, 900);
   public static String newResourceTitle_text6 = "Bloosom Mugs6"+TestBase.generateRandomNumberInRange(2, 900);
   public static String newResourceTitle_text7 = "Bloosom Mugs7"+TestBase.generateRandomNumberInRange(2, 900);
   public static String newResourceTitle_text8 = "Bloosom Mugs8"+TestBase.generateRandomNumberInRange(2, 900);
   public static String newResourceTitle_text9 = "Bloosom Mugs9"+TestBase.generateRandomNumberInRange(2, 900);
   public static String newResourceTitle_text10 = "Bloosom Mugs10"+TestBase.generateRandomNumberInRange(2, 900);
   public static String newResourceTitle_text11 = "Bloosom Mugs11"+TestBase.generateRandomNumberInRange(2, 900);
   public static String canadianResource = "Bloosom Mugs Canada"+TestBase.generateRandomNumberInRange(2, 900);
    
   public static String categoriesFilter = "div > #categories_id_container > button > span";
   public static String marketLanguageContainer = "//div[@id='market_language_container']/ul";
   public static String assetsTypeContainer = "//div[@id='resourceable_types_container']/ul";
   public static String filterTypesContainer = "//div[@id='filter_by_container']/ul";
   public static String sortOptionsContainer = "//div[@id='sort_by_container']/ul";
   public static String resourceOptions = "html/body/div[2]/div/div/div[1]/div/div[3]/div/div[1]/a";
   public static String marketsFilter = "div > #market_language_container > button > span";
   public static String assetsTypeFilter = "div > #resourceable_types_container > button > span";
   public static String drpdownFilterType = "div > #filter_by_container > button > span";
   public static String drpdownSortOption = "div > #sort_by_container > button > span";
   
   public static String newResourceDescription_text = "Bloosom mugs specifications";
   public static String newResourceFileType_text = "Image";
   public static String category_text = "All Category";
   public static String newResourceStatus_text = "Active";
   public static String newResourceStatus_text_Pending = "Pending";
   public static String btnAllStatuses = "div > #status_container > button > span";
   public static String chkNewResourceDisplayAsNewest = "//*[@id='pyr_core_resource_display_as_new']";
   public static String listStatuses = "//*[@id='status_container']/ul/li[@class='dropdown-option']/a";
   public static String inputDisplayAsNewest = "//*[@id='pyr_core_resource_display_newest_until']";
   public static String displayAsNewest_text = TestBase.getSystemDate();
   public static String resourceBLPane = "//*[@id='resource-manager-body']/div/div[2]";

   // public static String panelCategory = "//*[@id='resource_library']/div/ul/li";
   public static String panelCategory = "//*[@id='resource_library']/div/ul";
   public static String panelCatMaster = "//*[@id='main-content']/div/div[2]/ul/li/a";  //"div > div > div > ul > li > a";
   public static String panelCatBranch = "//*[@id='resource_library']/div/ul/li/a";
   public static String panelCat1 ="//*[@id='main-content']/div/div[2]/ul/li[";
   public static String panelCat1Branch ="//*[@id='resource_library']/div/ul/li[";
   public static String panelCat2 = "]"; //") > a";
   public static String panelPlus2 ="]/a[1]";   //") > a.dropdown.ic-icon-regular.ic-icon-plus";
   public static String datepickerClose = ".glyphicon.glyphicon-remove";
   
      

   /* *************  Vibe : Resource Library -> RL_DeleteNewResource  ****************************************/
  
   public static String divNoResourcesWarning = "//*[@id='resource-manager-body']/div/div[@class='alert alert-warning']";
   public static String resourceViewList = ".vibe-icon-list";
   public static String drpdnResourceSort = "//*[@id='sort_by_container']/button";
   public static String drpdnResourceFilter = "//*[@id='filter_by_container']/button";
   public static String tblResourcetable = "//*[@id='resource-table']/tbody";
  
   public static String resourceSort_text = "Newest";
   public static String resourceFilter_text = "All";
  
  
    // Resource View table.
     public static String drpdnSelectCategory =  "//*[@id='get_id']";
     public static String tblResourceView = "//*[@id='index_page']/div";
     public static String resourceHeader = "//*[@id='index_page']/div/div/div/div[3]/h4";
     public static String resourceBtn = "//*[@id='index_page']/div/div/div/div[1]/div/button";
    
     
     // Resource preview page
    
     public static String paneResourceStats =  "div.resource-main.modal-sidebar-content-pane > section.resource-statistics";
     public static String resourcePane = "div.resource-main.modal-sidebar-content-pane > section.modal-sidebar-content-controls";
     public static String elike = "div.col-md-6 > div.badge.badge-3";
     public static String btnResEmail = "//*[@id='page']/div[8]/div/div/div[2]/div[2]/div/div[2]/a[1]";
    
     public static String eShare = "div.col-md-6 > div.badge.badge-4";
     public static String btnResourceShare = "div.resource-main.modal-sidebar-content-pane > section.share-to-community.clearfix > div.pull-right > a.btn.btn-info.btn-sm.get-share";
     public static String txtareaShareComments = "//*[@id='pyr_crm_resource_library_comment_share_comment']";//"#pyr_crm_resource_library_comment_share_comment";
     public static String shareComments_text = newResourceTitle_text + " resource shared.";
    
     public static String btnCommentsShare = "//*[@id='new_pyr_crm_resource_library_comment_share']/div[2]/input";
     
     // Share Resource to Facebook
     
     public static String btnResourceShareSocialNetwork = "div.resource-main.modal-sidebar-content-pane > section  > div.pull-right > a.btn.btn-info.btn-sm.share-social-get-url.get-share";
     public static String btnShareFacebook = "a > span > svg.at-icon.at-icon-facebook";
     
     public static String inputFBEmail = "input#email.inputtext._55r1";
     public static String inputFBPwd = "input#pass.inputtext._55r1";
     public static String btnFBLogin = "#dialog_buttons > #loginbutton > input";    
     
     public static String btnFBShareLink = "div > #sharerDialogButtons > button:nth-child(2)";
     
     
     // Share a Resource
    
     public static String btnPreviewClose = "div.modal-tools > div.close-container > button.close > i";    // i.vibe-icon-close::before        // ".close";
     public static String btnEmail2WebSite = "section.email-a-link.clearfix > div.pull-right > a.btn.btn-info.btn-sm.email-url-get-url.get-compose";
                                                                                                                                       
     
     // Email a Resource
                         
     public static String inputComposeEmailTo = "//*[@id='email_message_editor']/form[1]/div/div[1]/div/input";     // "#recipients-to";  // div > div > div.mf_container";
     public static String inputComposeEmailSubject = "#email_message_subject";
     public static String composeEmailSubject_text = "Email Link To My Website "+TestBase.generateRandomNumberInRange(2, 200);
     public static String btnCompose = "section.email-a-link.clearfix > div.pull-right > a.btn.btn-info.btn-sm.email-url-get-url.get-compose";
             
    //*[@class='btn btn-info btn-sm email-url-get-url get-compose']";   //.btn.btn-info.btn-sm.email-url-get-url.get-compose";
   
     public static String btnOptions = "//*[@id='page']/div[7]/div/div/div[2]/div[1]/div[1]/button";
     public static String lnkPost2Community = "//*[@id='page']/div[7]/div/div/div[2]/div[1]/div[1]/ul/li[1]/a";
     public static String iconShare2Pwp = "//*[@id='page']/div[8]/div/div/div[2]/div[2]/div/div[2]/a[2]";
     
     /* ************* Communications >> CHAT***********************/
   
     
     public static String chatMessageText1 = "Hello,test "+TestBase.generateRandomNumberInRange(100, 200);;
     public static String chatMessageText2 = "your test no is "+TestBase.generateRandomNumberInRange(2, 99);
     public static String chatMessageText3 = "Recieved test Msg "+TestBase.generateRandomNumberInRange(1, 200);
     public static String CHAT = "Chat";
     public static String searchField = "";
     public static String chatSearchIcon = "";
     public static String newMessage = "a#new-chat-message";
     public static String usersList = "span.user-name";
     public static String chatRecipientsTo = "#recipients-container > form > div > div";
     public static String textArea = "textarea[id^='new_message_']";
     public static String sendMessage = "#send-dock-chat-message";
     public static String closeChat = "div.chat-title > div > i";
     public static String chatPanelTitle = "#community-inbox > div.panel-heading > div.panel-title";
     public static String chatTitleText = "Chat";
     public static String newMessageText = "New Message";
     public static String filterByText = "Filter By";
     public static String chatFilterDropDown = "div > div.col-md-4.no-gutter.chat-list > div.row > div:nth-child(2) > div > ul > li > a";
     public static String chatUser = "#chat-summaries > div > div > div.media-body";
     public static String chatTextAreaField = "span.message-container > textarea.auto-resize"; //"div > #chat-form > div.row > span.message-container > textarea.auto-resize";
     public static String chatSendBtn = "span.controls.chat-buttons > a.btn.btn-primary.btn-sm";
     public static String chatResourceIcon = "#dock-chat-form > div > a > i";
     public static String removeAttachIcon = "button > i";
     public static String removeAttach = "ul > li > a";
     public static String panelChatUsers = "//*[@id='chat-summaries']/div";
     public static String inputChatArea = "//*[@id='chat-form']/div[1]/span[1]/textarea";
     public static String quickChat = "div.chat-title > strong";
     public static String  chatPlus= "#chat-sidebar > div.chat-title > div.chat-sidebar-maximize > a >i";
     public static String  chatMinus= "#chat-sidebar > div.chat-title > div.chat-sidebar-minimize > i";
     public static String chatType = "#accordion > div > div.panel-heading > h4 > a";
     public static String chatUsersList = "li#chat-friend >  a > span.user-name";
     public static String chatUsersStatus = "li#chat-friend >  a > div.presence.on";
     public static String chatOffUsersStatus = "li#chat-friend >  a > div.presence";
     public static String filterBy = "button#filterid";
     public static String filterByInQC = "div.chat-friends-container > div > button#filterid";
     public static String filterOptions =  "ul.dropdown-menu.filter-by > li > a";
     public static String chatMsgs = "div.message.current-user";
     public static String qcTextMsg = "form#dock-chat-form > div > textarea";
     public static String qcSend = "input#send-dock-chat-message";
     public static String qcSave = "//*[@id='resources-modal']/div/div[1]/div[3]/button[1]";
     public static String qcAttach = "div.filename>a";
     public static String qcPendChatCount = "#pending-chats-count";
     public static String qcChatReq = "ul#pending-chats.list-group > li > span";
     public static String qcIncomUsers = "ul#pending-chats.list-group > li#incoming-chat-requests > a > span.user-name";
     public static String qcOutGoingUsers = "ul#pending-chats.list-group > li#outgoing-chat-requests > a > span.user-name";
     public static String req = "a#invite-to-chat.btn.btn-default.chat-with-user > span";
     public static String msgChatPlus = "#new-chat-message > i";
     public static String chatRecp = "#recipients-to";
     public static String chatCross= "div > div.chat-title > div.chat-tools > i.ic-icon-regular.ic-icon-times";
     public static String attachToggle = "div > button > i.ic-icon-regular.ic-icon-caret.";
     public static String removAttach = "div > ul > li > a";
     
     /****** 06/10/2016 ravi  *********************/
      public static String chatSearch = "#inbox-filter > input";
      public static String users = "#chat-summaries > div > div > div > span.user-name";    //"#chat-summaries > div > div > div.media-body > div.user-name > span";
      public static String usersBfr = "#chat-summaries > div > div:nth-child(";
      public static String usersAfr = ") > div > span.user-name"; //") > div.media-body > div.user-name > span";
 	 public static String msgList = "ul.clearfix > li";
     
     
   
   /************  Marketing >> Configure Ads           ********/
   

     public static String gotoAdmin = "Go To Admin";
     public static String marketingLnk = "Marketing";
     public static String configureAdsLnk	= "Configure Ads";
     public static String utube = "Upload Videos to Youtube";
     public static String addNewAdLnk ="#index_page > div > div.panel-heading > div.panel-tools > a";
     public static String  admarket = "#filter-market_container > button.btn.dropdown-toggle.btn.btn-default.btn-xs.filter"; // > span.button-drop-down-display-text";
     public static String  admarketDrpdwn = "#filter-market_container > ul > li > a";
     public static String  adlanguage= "#filter-language_container > button > span.button-drop-down-display-text";
     public static String  adlanguageDrpdwn = "#filter-language_container > ul > li > a";
     public static String  adSubcriptions= "#filter-subscription_container > button > span.button-drop-down-display-text";
     public static String  adSubcriptionsDrpdwn = "#filter-subscription_container > ul > li > a";
     public static String  adRank= "#filter-rank_container > button > i";
     public static String  adRankDrpdwn = "#filter-rank_container > ul > li.dropdown-option > a";
     public static String  adAvailable= "#filter-visible_container > button > span.button-drop-down-display-text";
     public static String  adAvailableDrpdwn = "#filter-visible_container > ul > li > a";
     public static String  adStatus= "#filter-status_container > button > i";
     public static String  adStatusDrpdwn = "#filter-status_container > ul > li > a";
     public static String  adShopCat= "#filter-taxon_container > button > i";
     public static String  adShopCatDrpdwn = "#filter-taxon_container > ul > li > a";
     public static String  adTurnOnShopBannerLabel ="table > tbody > tr > td.col-md-2 > label";
     public static String  adTurnOnShopBanner= "select#pyr_core_app_setting_value";
     public static String  adEdit= "";
     public static String  adDelete= "";
     
     public static String  adClickToUpload= "#new_pyr_core_banner > div > p#uploadClick";
     public static String  adURL= "div > div > div > input#pyr_core_banner_url";
     public static String  adOfficeVisibilityChkBox= "div.form-group.boolean.optional.pyr_core_banner_office_visibility > input#pyr_core_banner_office_visibility";
     public static String  adShopVisibilityChkBox= "div.form-group.boolean.optional.pyr_core_banner_shop_visibility > input#pyr_core_banner_shop_visibility";
     public static String  adTaxons= "#new_pyr_core_banner > div:nth-child(5) > div.field > div.panel.panel-admin-accordion > div.panel-heading > div";//"div:nth-child(6) > div.field > div.panel.panel-admin-accordion > div.panel-heading > div";
     public static String  adTaxonsDD="#taxon_checkbox > div > span >label"; //> label
     public static String  adTaxons1="#taxon_checkbox > div > span:nth-child(";
     public static String  adTaxons2=") > label"; //> label
     public static String  adStatusDD= "div:nth-child(7) > div > div > label";
     public static String  adStatusDropdown= "select#pyr_core_banner_status";
     public static String  adStatusDd= "select#pyr_core_banner_status > option";
     public static String  adStatusDdp= "select#pyr_core_banner_status > option:nth-child(1)";
     public static String  adStatusDdD= "select#pyr_core_banner_status > option:nth-child(2)";
     public static String  adStatusDdA= "select#pyr_core_banner_status > option:nth-child(3)";
     public static String  adsAllRank ="#rank_definitions > div > span > label";
     public static String  adRankDefine="#access_profile_settings > div:nth-child(1) > div.panel-heading > div" ;//"#new_pyr_core_banner > div:nth-child(7) > div.panel-heading > div";//"div:nth-child(8) > div.panel-heading > div";
     public static String  adRankDefineDD="#rank_definition_checkboxes > div > span > label"; 
     public static String  adMarketLang= "#access_profile_settings > div:nth-child(2) > div.panel-heading > div";//"#new_pyr_core_banner > div:nth-child(8) > div.panel-heading > div";//"#market_languages > div > div.label-with-hint > label";
     public static String  adMarketLangChkBox= "#market_language_checkboxes > div > span > label";
     public static String  adSubPlan= "//*[@id='access_profile_settings']/div[3]/div[1]/div";//"#new_pyr_core_banner > div:nth-child(3) > div.panel-heading > div";
     public static String  adSubPlanChkBox= "#subscription_plan_checkboxes > div > span > label";
     public static String  adAddBannerBtn= "#new_pyr_core_banner > input.btn.btn-primary";
     public static String  adAddBannerUpdate = "input.btn.btn-primary";
     public static String  adBackToAdsBtn= "a.btn.btn-primary";
     public static String  adsMarkText= "Please select at least one market.";
     public static String  comRankDef = "#rank_definition_checkboxes > div > span > label";
   
   

// *****************Marketing >> Company News *******************************//
   
   public static String compNewslnk=  "Company News";
   public static String compAddCompanyNews=  "#main-content > div > div.panel-heading > div.panel-tools > a";
   public static String compTitle=  "#pyr_core_company_news_title";
   public static String compExcerpt =   "#pyr_core_company_news_excerpt";
   public static String compSettings=  "#new_pyr_core_company_news > div > div.col-md-4 > div:nth-child(1) > div.panel-heading > div";
   public static String compIsNews =  "#pyr_core_company_news_is_news";
   public static String compIsHighLights  =  "input#pyr_core_company_news_is_highlight";//"#new_pyr_core_company_news > div > div.col-md-4 > div.panel.panel-default > div.panel-body > div.form-group.boolean.optional.pyr_core_company_news_is_highlight > label.boolean.optional.control-label";
   public static String compPublishArticle = "input#publish_now"; //"#new_pyr_core_company_news > div > div.col-md-4 > div.panel.panel-default > div.panel-body > span:nth-child(4) > label";
   public static String compPublishDate =  "#pyr_core_company_news_publish_date";
   public static String compPublishTime	 =  "#pyr_core_company_news_publish_time_field";
   public static String compPublishClock =  "button.btn.btn-default > span.ic-icon-regular.ic-icon-clock";
   public static String compScheduleArchive =  "label > input#archive_never";
   public static String compPreview =  "#new_pyr_core_company_news > div > div.col-md-4 > div.panel.panel-default > div.panel-body > span.action-buttons > input:nth-child(1)";
   //public static String compSaveAsDraft =  "#new_pyr_core_company_news > div > div.col-md-4 > div.panel.panel-default > div.panel-body > span.action-buttons > input:nth-child(2)";
   public static String compPublish =  "span.action-buttons > input.btn.btn-default.btn.btn-primary";  //.btn.btn-primary  div > div.col-md-4 > div.panel.panel-default > div.panel-body >
   public static String compThumbChooseFile = "#pyr_core_company_news_thumbnail"; //"//*[@id='pyr_core_company_news_thumbnail']";// "div > div > #pyr_core_company_news_thumbnail > #shadow-root > input";
   public static String compPostChooseFile=  "#pyr_core_company_news_file"; //"//*[@id='pyr_core_company_news_file']"; //"div > div > input#pyr_core_company_news_file > #shadow-root > input";
   public static String compAttachResource =  "#new_pyr_core_company_news > div > div.col-md-8 > a";
   public static String compTagList =  "#pyr_core_company_news_tag_list";
   public static String compBack =  "Back";
   public static String compSearch =  "#q_title_or_excerpt_or_content_cont";
   public static String compRank = "#access_profile_settings > div:nth-child(1) > div.panel-heading > div"; //"#new_pyr_core_company_news > div > div.col-md-4 > div:nth-child(2) > div.panel-heading > div";
   public static String compRankDD = "#rank_definitions > div > span > label";
   public static String compLang = "#access_profile_settings > div:nth-child(2) > div.panel-heading > div";//"#new_pyr_core_company_news > div > div.col-md-4 > div:nth-child(3) > div.panel-heading > div";
   public static String compLangDD = "#market_language_checkboxes > div > span > label";//"#market_languages > div > span > label > input";
   public static String compSubPlan = "#access_profile_settings > div:nth-child(3) > div.panel-heading > div"; //"#new_pyr_core_company_news > div > div.col-md-4 > div:nth-child(4) > div.panel-heading > div";
   public static String compSubPlanDD = "#subscription_plan_checkboxes > div > span > label";
   public static String companyNewsListTitle =  "div.media-body > h4 > a";
   public static String companyNewsViewListTitle =  "td.sorting_1 > a";
   public static String companyNewsList = "div.media-body > div";//"#main-content > div > div.panel-body";
   public static String compEdit = "div.panel-body > div.panel.panel-widget.panel-admin-widget > div.panel-heading > div.panel-tools > a.btn.btn-primary";//"div.panel-heading > div.panel-tools > a.btn.btn-primary > abc";  //"#main-content > div.panel.panel-widget.panel-admin-widget > div.panel-heading > div.panel-tools > a.btn.btn-primary"; 
   public static String compNewsThumb = "div.media-body > h4";
   public static String compListView = "//*[@id='main-content']/div/div[2]/div[2]/span/a/i";//"#main-content > div > div.panel-body > div.filter-and-sort > span > i.vibe-icon-list";
   public static String dropdownTable = "button.btn.dropdown-toggle.btn-default.btn-xs";
   public static String compDelete= "ul > li:nth-child(2) > a";
   public static String hover = "div.media-body > h5";
   public static String archDateField ="#pyr_core_company_news_archive_date";
   public static String archTimeField =  "#pyr_core_company_news_archive_time_field";
   public static String recent = "div#recent-posts > h3";
   public static String recPostList = "div#recent-posts > ul > li > a";
   public static String tagsInWid = "div#tags > h3";
   public static String tagList = "div#tags > ul > li > a";
   public static String titleNews = "div.panel-body > div.content > h2.news-title";
   public static String markAll = "input#market_all";
   public static String subAll =  "input#subscription_all";
   public static String rankAll = "input#rank_all";
   public static String adsPubDate = "input#pyr_core_banner_publish_date";
   public static String adsExpDate = "input#pyr_core_banner_expiry_date";
   public static String adsPubText = "#publish_now_section > label";
   
   
 //****************** Admin >> Widgets ***************************************************** //
   
// public static String adminWidgetsTable = "div.panel-body > table.table.widgets_table > tbody";
  public static String adminWidgetsTable = "table.table.widgets_table > tbody";
   
   public static String chkWidgetActive = "div > div > label > input#pyr_core_widget_active.boolean.optional";
   public static String inputUpdateWidget = ".actions>input";
   
   public static String widgetsPane = "ul.source-widgets.scrollable-widget-results.list-group";  // div.input-group input-group-lg > ul.source-widgets.scrollable-widget-results.list-group";
 //public static String homepageWidgetContainer = "//*[@id='widget_manager_home']/div[2]"; 
   public  static  String homepageWidgetContainer  = "//*[@id='widget_manager_home']/div[2]/div/div[@class='row']";
   public static String homepageDiv1Container = "//*[@id='widget_manager_home']/div[2]/div/div/div[1]/div";
   											//*[@id='widget_manager_home']/div[2]/div/div[@class='row']/div[1]/div[1]";
                                                 
   public static String homepageDiv2Container =   "//*[@id='widget_manager_home']/div[2]/div/div/div[2]";
   public static String widgetCloseIcon = ".fa.fa-trash-o";
   
 //added on START#18-Jan-2016
   
   // Subscription Plan
   
   public static String inputSubscriptionName = "//*[@id='subscription_plan_name']";
   public static String inputSubscriptionPrice = "//*[@id='subscription_plan_price']";
   public static String btnCreateSubscriptionPlan = "//*[@id='new_subscription_plan']/input[3]";
   
   public static String subscriptionName_text = "New_Subscription_" + TestBase.random();
   public static String subscriptionPrice_text = "100";
   
   public static String tblSubscriptionplan = "//*[@id='main-content']/form/table[1]/tbody";
   public static String tblSubscriptionSelectMarket = "//form[@class='simple_form edit_pyr_core_subscription_plan']/div[@class='form-group check_boxes optional pyr_core_subscription_plan_market_ids']";
   public static String btnSaveSubscriptionSelectMarket = "//*[@id='subscription-plan-markets-modal']/div/div[1]/div[3]/button";
   
   
   public static String tblAvailableSubscriptions = "//*[@id='_new_billing_form']/div[2]/div[2]";
   
   
   // ******************************  Admin Reports **************************************************** 
   
   // Admin New Reports
   
   public static String  inputReportName = "//*[@id='reports_report_title']";
   public static String  textareaReportDesc = "//*[@id='reports_report_description']";
   public static String  inputActiveStart = "//*[@id='reports_report_active_start']";
   public static String  chkReportNoEndDate = "//*[@id='reports_report_no_end_date']";
   public static String  btnContinue = "//*[@id='new_reports_report']/p/input";
   public static String  divSecurityRoles = "//*[@id='new_reports_report']/div/div[2]/div";
   
   public static String  reportName_text = "Vibe_General_Report_" + TestBase.random();
   public static String  reportDesc_text = reportName_text + " description.";
   public static String  activeStart_text = TestBase.getSystemDate();
   
   
   public static String  eleTreePlacement = "//*[@id='main-content']/div[2]/div[1]/div[3]/label";
   public static String  chkUplineConstraint = "//*[@id='main-content']/div[2]/div[1]/div[3]/span/table/tbody/tr[2]/td[2]/input";
   public static String  chkOutputConstraint = "//*[@id='main-content']/div[2]/div[1]/div[3]/span/table/tbody/tr[2]/td[3]/input";
   public static String  btnP2Continue = "//*[@value='Continue']";
   
   public static String  lnkReportPage4 = "//*[@id='main-content']/div[1]/ul/li[4]";
   public static String  chkReportPublished = "//*[@id='reports_report_published']";
   public static String  btnReportSubmit = "div > div > input.btn.btn-primary";
   
   public static String  tblReport = "//*[@id='main']/div[3]/div/div/div/div/table/tbody";
   
   
   // **************************** Admin -> Shop *****************************************************
   
   public static String  lnkAdminNewProduct = "//*[@id='admin_new_product']";
   
   // Add New Product page
   
   public static String  inputShopProductName = "//*[@id='product_name']";
   public static String  inputShopProductSKU = "//*[@id='product_sku']";
   public static String  inputShopMasterPrice = "//*[@id='product_price']";
   public static String  eleShopShippingCategory = "//*[@id='s2id_product_shipping_category_id']/a";
   public static String  inputSearchShippingCategory = "//*[@id='s2id_autogen2_search']";
   public static String  inputShopProductAvailableOn = "//*[@id='product_available_on']";
   public static String  btnShopProductCreate = "form#new_product.new_product > fieldset > div.form-actions > button.btn.btn-primary.btn-success";
   
   public static String  divShopMarketCheckboxGroup = "div > div#market_language_checkbox.indented-checkbox-group";
   public static String  eleShopTaxons = "//*[@id='s2id_product_taxon_ids']/ul";
   public static String  drpdnShopTaxons = "//*[@id='select2-drop']/ul";
   public static String  inputShopProductTaxons = "#product_taxons_field > #s2id_product_taxon_ids > ul > li > input#s2id_autogen3.select2-input.select2-default";
   
   public static String  lnkShopVariants = "//*[@id='sidebar']/ul/li[3]/a";
   public static String  lnkShopAddNewVariant = "//*[@id='new_var_link']";   
   public static String  btnShopProductUpdate = "div.form-actions > button.btn.btn-primary.btn-success";
   
   public static String  shopProductName_text = "Prod_" + TestBase.random();
   public static String  shopProductSKU_text = "SKUID" + TestBase.random();
   public static String  shopShippingCategory_text = "Default";
   public static String  shopMasterPrice_text = "199";
   public static String  shopProductAvailableOn_text = TestBase.getSystemDate();
   
   public static String  shopVProductName_text = "VProd_" + TestBase.random();
   public static String  shopVProductSKU_text = "SKV" + TestBase.random();
   public static String  shopVShippingCategory_text = "Default";
   public static String  shopVMasterPrice_text = "399";
   public static String  shopVProductAvailableOn_text = TestBase.getSystemDate();
   
   // Shop -> Place Order page.
   
   public static String  shopProductsPanel = "//*[@id='products']";

   // added on  END#18-Jan-2016
   
   /* ***********Communications >>> Inbox  *************************/
   
   public static String COMMUNICATIONS_Tab = "//*[@id='navbar-collapsable']/ul/li[3]/a/span[2]";
   public static String INBOX = "Inbox"; 
   
   public static String inboxPanel = "div#email-navigation";
 //  public static String inboxPanel = "//*[@id='main-content']/div/div[2]";
   public static String Inbox = "#email-navigation > ul:nth-child(1) > li.active > a";
   //public static String Inbox = "div#email-navigation > ul:nth-child(1) > li:nth-child(1) > a";
   public static String Inbox_NewMails ="div#email-navigation > ul:nth-child(1) > li.active > div";
   public static String starred ="div#email-navigation > ul:nth-child(1) > li:nth-child(3) > a";
   public static String content = "form#bulk-form > table > tbody > tr > td > h4";
  
   //public static String compose ="Compose";
   public static String compose = "div#email-navigation > ul:nth-child(1) > li:nth-child(1) > a.btn.btn-primary";
   public static String important="div#email-navigation > ul:nth-child(1) > li:nth-child(4) > a";
   public static String sent="div#email-navigation > ul:nth-child(1) > li:nth-child(5) > a";
   public static String attachments="div#email-navigation > ul:nth-child(1) > li:nth-child(6) > a";
   public static String trash="div#email-navigation > ul:nth-child(1) > li:nth-child(7) > a";
   public static String business="";
   public static String team ="";
   public static String downline ="";
   public static String contact ="";
   public static String labels ="";
   public static String createLabels ="div#email-navigation > ul:nth-child(7) > li > a";
   public static String ADDLABELHEADER = "Add Label";
   public static String addLabelHeader = "div.modal-header > h1#label-for-addLabelModal";
   public static String addLabelName = "input#new_label_name";
   public static String addLabelSave = "div.modal-footer > button";
   public static String labelAllNames = "//*[starts-with(@id,'email-navigation']/ul[3]/li[)]/a";
   public static String deleteLabel= "//*[@id='email-navigation']/ul[3]/li[1]/span/a";
   public static String deleteToOk = "body > div.bootbox.modal.fade.bootbox-confirm.in > div > div > div.modal-footer > button.btn.btn-primary";
   public static String deleteToCancel = "body > div.bootbox.modal.fade.bootbox-confirm.in > div > div > div.modal-footer > button.btn.btn-default";
  
   public static String recipientsTo = "div > div > input#recipients-to";
  
   public static String recipTo ="//*[@id='invitationModal']/div/div[1]/div[2]/form[1]/div/div/input";
   public static String btnSendInvite = "//*[@id='invitationModal']/div/div[1]/div[2]/form[2]/div[4]/input";
 //  public static String subject_Mail = "input#email_message_subject";
   public static String message_IFrame = "#cke_1_contents > iframe";
  // public static String message_Mail =   "iframe > html > body.cke_editable.cke_editable_themed.cke_contents_ltr.cke_show_borders";
 // public static String message_Mail =  "body"  ;       // "email_message_subject"; //"//*[@id='cke_email_textarea']";
  public static String message_Mail = "//*[@id='new_pyr_crm_message']/div[7]";

  public static String btnAttachFile = "//*[@id='email_message_buttons']/a[1]";
  public static String btnSend = "//*[@id='email_message_buttons']/a[3]";

   public static String send_Mail = "div#email_message_buttons > a.btn.btn-primary.btn-sm";
 //public static String attachAFile ="div#email_message_buttons > a:nth-child(2)";
  public static String attachFileFrame = "//*[@id='upload_file_modal']/div/div[1]/div[3]";
  public static String attachAFile ="//*[@id='email_message_buttons']/a[2]";
  public static String attachToMessge = "//*[@id='upload_file_modal']/div/div[1]/div[3]/button[1]";

  public static String insertIntoMessageEditor= ".//*[@id='upload_file_modal']/div/div[1]/div[3]/button[2]";

 //  public static String attachMailCLOSE= "//*[@id='upload_file_modal']/div/div[1]/div[3]/button[3]";

  //public static String attachAFile ="";
   public static String attachResourceFiles ="";
   public static String mailSelectionSort ="div#bulk-controls-container > div.btn-group > button";
   public static String mailSummary = "td.email-excerpt-column.email-clickable";
   public static String mailReply = "div#email-panel > div > div.email-header.row > div.col-md-6.text-right > div.mail-actions > a:nth-child(1) > i";
   public static String mailReplyAll = "div#email-panel > div > div.email-header.row > div.col-md-6.text-right > div.mail-actions > a:nth-child(2) > i";
   public static String mailForward = "div#email-panel > div > div.email-header.row > div.col-md-6.text-right > div.mail-actions > a:nth-child(3) > i";
   //public static String mailSelectionDropDwonFrame= "div#bulk-controls-container > div.btn-group.open > ul";
   public static String mailSelectionDropDwonFrame = "//*[@id='bulk-controls-container']/div[1]/ul";
   public static String ALL_Text = "All";
   public static String NONE_Text = "None";
   public static String READ_Text = "Read";
           
   //public static String search ="";
  // public static String searchIcon ="";
   public static String mailsPerpageSelection ="";
  
  /* ************************** Inbox - GMail ************************************************************************************************* */
   
    public static String inputGmail = "div > input#Email";
    public static String chkStaySignedIn = "//*[@id='PersistentCookie']";
    public static String inputGmailPasswd = "div > input#Passwd";
    public static String btnGmailNext = "div.slide-out > input#next.rc-button-submit";
    public static String btnSignIn =  "#signIn";   //div.slide-in > input#signln.rc-button.rc-button-submit";
    public static String imgGmailLoadingbar = "div.cmsg > div.lpb";
    public static String drpdnGmailUserProfile = "//span[@class='gb_9a gbii']";     
   
    public static String tblGmailBody = "//*[@class='F cf zt']/tbody";  // "div.Cp > div > table";  // "div.Cp > div > table#:3b.F.cf.zt";
    public static String tblGmailSummary = "//td[@class='yX xY']";             // "//table[@id=':3b']/tbody/tr[2]/td[6]/div/div/div/span[@class='y2']";
   
    public static String btnGmailSignout = "div.gb_4a > div > a#gb_71.gb_0c.gb_7c.gb_xa";
    public static String gmailAccountList =  "//*[@id='gaia_loginform']/ol";    // "//*[@id='account-list']";
   
    
    public static String gmailId_text = "vibe.icentris@gmail.com";
    public static String gmailPwd_text = "vibe@123";
   
    public static String labelInbox = "//*[@id=':4q']/div/div[1]/span/a";
    public static String btnComposeGmail = "div.z0 > div.T-I.J-J5-Ji.T-I-KE.L3";
    public static String wndComposeBody = "//div[@class='aaZ']";
    public static String eReceipients = "//div[@class='aoD hl']";
    public static String eSubjects = "//div[@class='aoD az6']";
    public static String inputComposeTo = "//*[@class='vO']";                       // "//input[@class='wA']";    // ; // "//*[@id=':7g']";
    public static String inputComposeSubject = "//input[@class='aoT']";
    public static String inputComposeDesc = "//div[@class='Am Al editable LW-avf']";     // "//*[@id=':6t']";
    public static String btnComposeSend =    "//div[@class='T-I J-J5-Ji aoO T-I-atl L3']";  //"div.T-I.J-J5-Ji.aoO.T-I-atl.L3";   // "//div[@class='T-I J-J5-Ji aoO T-I-atl L3']";
                                                                      
    public String composeTo_text =  "jayadev@vibeofficestage.com";
   
    
    public static String composeSubject_text =  "Mail from Gmail_" + TestBase.random(); //"mail to vibe user_52"; 
    public static String composeDesc_text = "sending mail from gmail to vibe user account.";


   // public static String inputGmailPasswd1 = "#Passwd";
    
 /* ************************** Communications -> Inbox ************************************************************************************************* */
 
    public static String lblInboxPaneTitle = "div.col-md-7 > div.panel-title";
    public static String tblInboxBody = "//*[@id='bulk-form']/table/tbody";
     
    public static String panelQuickLinks = "//*[@id='user-control-panel']/div[2]/div";
   
  //  public static String divInboxCount = "//*[@id='user-control-panel']/div[2]/div/a[4]/div";
    public static String divInboxCount = "//*[@id='special-menu']/li[2]/a/div";
    
    public static String btnFilterInboxEmail = "//*[@id='bulk-controls-container']/div[1]/button";
    public static String btnDeleteInboxMail = "//*[@id='bulk-controls']/div[1]/button";
   
   // public static String btnToggleMailsperPage =  "//*[@id='main-content']/div/div[1]/div[2]/div/div[2]/div/div/span/button";
                

    public static String btnToggleMailsperPage =    "div.search-options.input-group > span > button.btn.btn-default.dropdown-toggle";
    
    public static String lnkInbox = "//*[@id='email-navigation']/ul[1]/li[1]/a";
    public static String lnkStarred = "//*[@id='email-navigation']/ul[1]/li[2]/a";
    public static String lnkImportant = "//*[@id='email-navigation']/ul[1]/li[3]/a";
    public static String lnkSent = "//*[@id='email-navigation']/ul[1]/li[4]/a";
    public static String lnkAttachments = "//*[@id='email-navigation']/ul[1]/li[5]/a";
    public static String lnkTrash = "//*[@id='email-navigation']/ul[1]/li[6]/a";
    
    
    public static String btnApplyLabel = "//*[@id='bulk-controls']/div[2]/button";
    public static String inboxSidePane = "//*[@id='main-content']/div/div[2]";
   
    public static String inputLabelName = "form#add_label_form  > div.form-group.string.required.bulk_param > input#bulk_param"; //"//*[@id='new_label_name']";
   
    public static String lnkCreateNew = "//*[@id='email-navigation']/ul[3]/li[2]/a";
    public static String labelName_text = "L1" + TestBase.random();
    public static String btnLabelSave =  "div#addLabelModal > div.modal-dialog > div.modal-content > div:nth-child(3) > button";         // "//*[@id='addLabelModal']/div/div[1]/div[3]/button";
    public static String btnOK = "div.modal-content > div.modal-footer > button.btn.btn-primary";
   
    // Communications -> Inbox -> Compose
   
    public static String inputVibeComposeTo = "#email_message_editor > form > div > div > div.mf_container";
    public static String subject_Mail = "input#email_message_subject";
    public static String attachResourceFile ="//*[@id='email_message_buttons']/a[3]";
    public static String subject_Mail_text = "Vibe mail_" + TestBase.random();
    public static String txtGmailSubject = "Gmail_" + TestBase.random();
    public static String txtVibeEmailSubject = "Vibe_Mail_" + TestBase.random();
    
 // Communications -> Inbox -> Compose // Attach File
    
    public static String btnBrowseFile = "//*[@id='pyr_crm_message_file']";
 //  public static String attachToMessge = "//*[@id='upload_file_modal']/div/div[1]/div[3]/button[1]";
 //  public static String attachMailCLOSE= "//*[@id='upload_file_modal']/div/div[1]/div[3]/button[3]";
    public static String attachMailCLOSE= "//*[@id='upload_file_modal']/div/div[1]/div[3]/button[3]";
   
 // Communications -> Inbox     // Inbox Mail body
   
   public static String inboxPanelBody = "//*[@id='bulk-form']/table/tbody";
   
    public static String inboxNoEmails = "//*[@id='bulk-form']/table/tbody/tr/td/h4";
    public static String inboxNoEmails_text = "You Don't Have Any Messages";
   
 // Communications -> Inbox     // Apply Labels
   
    public static String labelControl = "//*[@id='bulk-controls']/div[2]/ul";
   
 // Communications -> Inbox     // Delete Labels
   
    public static String lableSidePane = "//*[@id='email-navigation']/ul[3]";
   
 // Communications -> Inbox -> Compose  -> Attach Resource File
   
    public static String prodCategoryPane = "//*[@id='resources-modal']/div/div[1]/div[2]/div/div[1]/ul";
    public static String resPane = "//*[@id='resources-modal']/div/div[1]/div[2]/div/div[2]/div[3]";
    public static String btnRescSave = "//*[@id='resources-modal']/div/div[1]/div[3]/button[1]";
   
   
    // Search Inbox
    public static String inputSearch = "//*[@id='search_q']";
    public static String btnSearch = "//*[@id='calendar']/div[1]/div[3]/div/div[2]/span/button";
    
    
    // Inbox More filter
    
    public static String btnMoreFilter = "//*[@id='bulk-controls']/div[3]/button";
    public static String btnDeleteForEver = "//*[@id='bulk-controls']/button";
    
    
    // Unsubscribe page
    
    public static String inputToMail = "#pyr_crm_opt_out_email";
    public static String drpdnOptStatus = "#pyr_crm_opt_out_status";
    public static String inputFromMail = "#pyr_crm_opt_out_opted_out_from_user_id";
    public static String btnUnsubscribeSubmit = "div.main-content-column  >  form.simple_form.new_pyr_crm_opt_out > input";
    public static String msgOptStatus = "#opt-out-status";
    
    
   /************new scripts*********************************************/
    /******************Goals*****************************************/
    
    public static String goal = "Goals"; 
    public static String addGoal = "Add Goal";
    public static String completeBy = "#pyr_community_goal_complete_by";
    public static String saveGoal ="div#create_goal > div > div.modal-content > div.modal-footer > button.btn.btn-primary"; //"//*[@id='create_goal']/div/div[1]/div[3]/button";//"div#create_goal > div > div.modal-content > div.modal-footer > button.btn.btn-primary";
    public static String Description_text = " Count No of Days "+ TestBase.generateRandomNumberInRange(1, 256);
    public static String showOnBrd = "#new_pyr_community_goal > div.form-group.boolean.optional.pyr_community_goal_show_on_board > div > label";
    public static String goalTitle = "#pyr_community_goal_title";
    public static String goalDesc  = "#pyr_community_goal_description";
    public static String goalUpload  = "#pyr_community_goal_image";
    public static String Title_text = "Awesome goals to reach in " + TestBase.generateRandomNumberInRange(1, 256) + " Days";
    public static String goalsList = "div > div > h3 > a"; //"#goal_listing > div.row.goal-details > div > div > h3 > a";
    public static String deletelnk  = "div.community_activity_action.like-comment-links > span> a";  //:nth-child(4) 
    public static String tasksPanel = "tbody > tr > td.task-title > a";//"#goal_listing > div.row.goal-details > div.col-md-12.goal_ui.goal48 > div:nth-child(4)";
    public static String browseTochangePhoto = "div > input#pyr_core_profile_cover_photo";
   
    /******************************BUSSINESS >> ENROLLMENT *******************************************************/
    
    public static String enrollemtTab= "Enrollment";
    public static String chooseMarket= "#main-content > div > div > div > div > div";
      public static String sponserName= "section > div:nth-child(1) > div > div";
   
    public static String TOCChkbox= "section > div.row.terms-and-conditions > div > div > div > label";
    public static String continueKitSelectEnroll= "div > div.col-md-12 > div.continue > a.btn.btn-primary.btn.btn-enrollment-continue"; //div > div.col-md-12 > div.continue > a.
    public static String ContinuePerInfoEnroll="html/body/div[1]/div/div[2]/div[2]/div[2]/form/div/div/div/a"; 
    public static String continueProdSelectEnroll = "html/body/div[1]/div/div[2]/div[3]/div[2]/form/div/div/div/a";
    public static String conInBill="html/body/div[1]/div/div[2]/div[4]/div[2]/form/div/div/div/a";
    public static String conInshipInfo="html/body/div[1]/div/div[2]/div[5]/div[2]/form/div/div/div/a";
    public static String conInshipMeth="html/body/div[1]/div/div[2]/div[6]/div[2]/form/div/div/div/a";
    public static String selectPack= "";
    public static String autoshipQuantity= "";
    public static String Quantity= "";
    
    public static String selectItem= "";
    public static String autoshipDate= "";
    public static String state1 = "California";
    public static String state2 = "Florida";
    public static String state4 = "Connecticut";
    public static String state3 = "Indiana";
    public static String state5 = "Georgia";
    
    public static String   selectMarket="div.col-md-2.text-center>a>h3";
    public static String   yesForLifeVantage="#revel_enrollment_check_form_history_check_yes";
    public static String   NoForLifeVantage="#revel_enrollment_check_form_history_check_no"; 
    public static String   yesForCurrentLifeVantage="#revel_enrollment_check_form_current_check_yes";
    public static String   NoForCurrentLifeVantage="#revel_enrollment_check_form_current_check_no";
    public static String   EndedDate="#revel_enrollment_check_form_termination_date"; 
    public static String   kitPacks="section > div.form-group.string.optional.pyr_core_v2_enrollment_sk_sku > div.starter-kits-table-container > div > div.col-md-6.starter-kit-description > h3";
    public static String   kitPackSelectBtn="div.form-group.string.optional.pyr_core_v2_enrollment_sk_sku > div.starter-kits-table-container > div > div.col-md-3.pricing > div > label";
    public static String   panelPac="//section/div[2]/div[2]"; 
    public static String   eFirst="#pyr_core_v2_enrollment_first_name";
    public static String   eLast="#pyr_core_v2_enrollment_last_name";
    public static String   eSSN="#pyr_core_v2_enrollment_tax_id"; 
    public static String   eCell="#pyr_core_v2_enrollment_cell_phone";
    public static String   eHome="#pyr_core_v2_enrollment_day_phone";
    public static String   eEmail="#pyr_core_v2_enrollment_email"; 
    public static String   eConfirmEmail="#pyr_core_v2_enrollment_confirm_email";
    public static String   eUserName="#pyr_core_v2_enrollment_username"; 
    public static String   eConfirmUserName="#pyr_core_v2_enrollment_confirm_username";
    public static String   ePswd="#pyr_core_v2_enrollment_password";
    public static String   eConPswd="#pyr_core_v2_enrollment_confirm_password"; 
    public static String   eSt1="#pyr_core_v2_enrollment_tmp_main_address_address1";
    public static String   eSt2="#pyr_core_v2_enrollment_tmp_main_address_address2";
    public static String   eCity="#pyr_core_v2_enrollment_tmp_main_address_city"; 
    public static String   ePost="#pyr_core_v2_enrollment_tmp_main_address_postal_code";
    public static String   eBirthDay="#pyr_core_v2_enrollment_birthday"; 
    public static String   eGender="select#pyr_core_v2_enrollment_gender";
    public static String   eState = "select#pyr_core_v2_enrollment_tmp_main_address_state";
    public static String   eCountry = "#pyr_core_v2_enrollment_tmp_main_address_country";
    public static String   eStateOptions = "select#pyr_core_v2_enrollment_tmp_main_address_state > option";
    public static String   BussinessTab ="Tools";   
    public static String   message ="html/body/div[1]/section[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/form/section";

    /******************************Company News *********************/

 	public static String compNewsToasterText = "Company News is Created.";
 	public static String companyNewUpdated = "Company News is Updated."; 	
 	public static String newsDeletedText = "Company News is Deleted.";
    public static String title = "Maximizing Social Media Marketing By "+TestBase.generateRandomNumberInRange(11, 500) + " Ways";
 	public static String excerpt = "Excerpt"+TestBase.generateRandomNumberInRange(11, 500)+" Ways";
 	public static String excerpt2 = "UnPublished Excerpt "+TestBase.generateRandomNumberInRange(11, 500);
 	public static String title2 = "Connect and Grow Your Community By "+TestBase.generateRandomNumberInRange(11, 500) + " Ways";
 	public static String title3 = "Future comes in "+TestBase.generateRandomNumberInRange(11, 500);
 	public static String title4 = "Today "+TestBase.generateRandomNumberInRange(11, 500);
 	public static String title5 = "UnPublished News "+TestBase.generateRandomNumberInRange(1, 500);
 	public static String proBundle = "Pro Bundle";
 	public static String bronze = "Bronze 1";
    public static String excerptDesc= "div.media-body > div.content";
    public static String delInfo= "body > div.bootbox.modal.fade.bootbox-confirm.in > div > div > div.modal-footer > button.btn.btn-primary";
    public static String closeWidgetInHomeMaster = "div#widget_manager_home.widget-manager> div.widget-manager-header > div > div.btn-group > a.btn.btn-default > i.fa.fa-close";               //ic-icon-regular.ic-icon-close";            //fa.fa-close";
    public static String closeWidgetInHomeBranch = "a.btn.btn-default > i.ic-icon-regular.ic-icon-close";               //ic-icon-regular.ic-icon-close";            //fa.fa-close";
    public static String vieMorelnk="#company_news > div.panel-body > div.panel-footer > a";
    public static String sortMarking = "#filter-market_container > button > span.button-drop-down-display-text";
    public static String sortMark = "#filter-market_container > ul > li.dropdown-option";
    public static String sortMark1 = "#filter-market_container > ul > li:nth-child(";
    public static String sortMark2 = ") > a";
    public static String sortLangsdp ="#filter-language_container > button > span.button-drop-down-display-text";
    public static String sortLangs ="#filter-language_container > ul > li > a";
    public static String sortSubsdp = "#filter-subscription_container > button > span.button-drop-down-display-text";
    public static String sortSubs ="#filter-subscription_container > ul > li > a";
    public static String sortRankdp = "#filter-rank_container > button > span.button-drop-down-display-text";
    public static String sortRank ="#filter-rank_container > ul > li > a";
    public static String sortTypedp ="#filter-type_container > button > span.button-drop-down-display-text";
    public static String sortType ="#filter-type_container > ul > li > a";
    public static String sortStdp ="#filter-status_container > button > span.button-drop-down-display-text";
    public static String sortSt ="#filter-status_container > ul > li > a";
    public static String sortTagdp ="#filter-tag_container > button > span.button-drop-down-display-text";
    public static String sortTag ="#filter-tag_container > ul > li.dropdown-option > a";   
    public static String newsInfo  ="#main-content > div > div.panel-body > div.content > a";
    public static String logoutHere ="div#page > div.caution_msg > a";    
    public static String delAds="div#banners > div > div > div.links > a.ic-icon-regular.ic-icon-edit" ;
    public static String sortmarket= "#filter-market_container > button > i";
    public static String sortSub = "#filter-subscription_container > button > i";
    public static String sortShop = "#filter-taxon_container > button > i";
    public static String sortBanner = "div > div > select#pyr_core_app_setting_value";
    public static String sortBannerNo = "//*[@id='pyr_core_app_setting_value']/option[2]"; //"div > div > select#pyr_core_app_setting_value > option";  //
    public static String sortLang = "#filter-language_container > button > i";
    public static String sortRanks = "#filter-rank_container > button > i";   
    public static String sortStatus = "#filter-status_container > button > i";
    public static String sortAvail = "#filter-visible_container > button > i";
    public static String adswidgetInMaster = "div#widget-ads > div > div > div > div.item.active > a";  //> img
    public static String adswidgetInBranch = "div#widget-ads > div > div > div > div.item.active > a";
    public static String adsWidgetSettings = "//div[1]/span[6]/i"; 
    public static String adsWidgetinShop = "div#widget-ads > div > div > div.item > a";
    public static String adswidgetSettings1  = "div[id$=pyr_core_widgets_carousel_ads_id_]";
    public static String adswidgetSettings2  ="> div.widget-controls > span:nth-child(6) > i";
    
    public static String urlAds1 = appUrl+"/"+TestBase.generateRandomNumberInRange(11, 550);	
    public static String urlAds2 = appUrl+"/"+TestBase.generateRandomNumberInRange(1, 9);
    public static String urlAds3 = "http://programmingsimplified.com/";
    public static String urlAds4 = "https://gotomeeting.com/";
    public static String urlAds5 = appUrl+TestBase.generateRandomNumberInRange(100, 200);
    public static String urlAds6 = appUrl+TestBase.generateRandomNumberInRange(300, 500);
    
    public static String editAds1 ="html/body/div[1]/section/div[2]/div/div/div/div/div/div/div[4]/div/div/div[";
    public static String editAds2 ="]/div/a[1]";
    public static String adsImgNext = "div#widget-ads > div > div > a.right.carousel-control > span.glyphicon.glyphicon-chevron-right";
    public static String imz1=     "div#widget-ads > div > div > ol > li:nth-child(";
    public static String imz2= ")" ;
    public static String imzShop1= "div#widget-ads > div > ol > li:nth-child(";
    public static String imageDot = "div  > ol.carousel-indicators > li";   //"div#widget-ads > div > div > ol > li";
    public static String imageDots = "//*[@id='carousel-ads']/ol /li";

    public static String ranker5 = "Bronze 1";

    public static String ranker1 = "Bronze 1";
    public static String ranker3 = "Platinum 1";   
    public static String ranker2 = "Silver 1";
    public static String ranker4 = "Gold 1";
    public static String language1 = "US (English)"; 
    public static String language2 = "CA (French)";
    public static String st1 = "Published";
    public static String st2 = "Draft";   
    
 /*   public static String[] subs2 = {"Vibe Pro - Yearly"};
    public static String[] subs3 = {"Vibe Lite - Monthly"};
    public static String[] subs4 = {"Vibe Lite - Yearly"};*/
    
/*    public static String[] subs5 = {"All"};*/
    public static String noSelection = null;
    public static String avail1= "Office";
    public static String avail2 = "Shop";
    public static String shopAdsCarousel= "#carousel-ads > div > div > a > img";
    public static String imageDotAtShopping = "div#widget-ads > div > ol > li";
    public static String alertmsgAds ="#new_pyr_core_banner > div.form-group.file.required.pyr_core_banner_image.has-error > span";
    public static String VibeProMonthlyUser ="raj.19pro1"; ///"raghavanrocks";  // branch - rajesh.10pro4  -master-- rajesh.mprod1
    public static String vibeProYearlyUser = "ramesh.buridi" ; //"rameshburidi";  // pratima.b1005
    
    //shopping
    
    public static String custShopPwdInBranch ="div.col-md-6.col-md-offset-3 > div.form-group > input#user_password"; //"div.col-md-4.col-md-offset-4 > div.form-group > input#user_password";
    public static String custShopPwdInMaster = "div.form-group.password.required.user_password > input#user_password";
    
    
    
    public static String markOkBtn = "div > div.modal-footer > button.btn.btn-default";  
    public static String selectMark = "select#market";
    public static String frameMarket = "#market_selection_modal_popup > div > div.modal-content";
    public static String loginBtn = "#new_user > div:nth-child(5) > div > input";
    public static String loginSucessMsg = "You have successfully signed in.";
    public static String registerNew = "ul#customer-tabs > li:nth-child(1) > a";
    public static String returnExist = "#customer-tabs > li:nth-child(2) > a";
    public static String custFN = "#user_profile_attributes_first_name";
    public static String custLN = "#user_profile_attributes_last_name";
    public static String custEmail = "#user_email";
    public static String custPhone = "#user_profile_attributes_phone";
    public static String custSpnserId = "#user_profile_attributes_sponsor_id";
    public static String custAd1 = "#user_profile_attributes_address_attributes_address1";
    public static String custAd2 = "#user_profile_attributes_address_attributes_address2";
    public static String custCity = "#user_profile_attributes_address_attributes_city";
    public static String custCounty = "#user_profile_attributes_address_attributes_county";
    public static String custState = "select#user_profile_attributes_address_attributes_state";
    public static String custStateList = "select#user_profile_attributes_address_attributes_state > option";
    public static String custCntry = "select#user_profile_attributes_address_attributes_country";
    public static String custZip = "#user_profile_attributes_address_attributes_zip";
    public static String custUserName = "#user_username";
    public static String custPwd = "#user_password";
    public static String custConPwd = "#user_password_confirmation";
    public static String custDOB = "#user_profile_attributes_birthday";
    public static String custGender = "#user_profile_attributes_gender";
    public static String custGenderOpt = "#user_profile_attributes_gender > option";
    public static String custCreate = "form#new_user > div > div.row > div.col-sm-2 > input.btn.btn-primary";
    public static String custFNText = "atom."+TestBase.generateRandomNumberInRange(2,3000);   
    public static String custLNText = "Test";
    public static String custPwdText = "test123";
    public static String moreIcon = "div.atc-qty > div > div.number-spinner-more";
    public static String  miniViewCartYevo ="#minicart-footer > div.pull-left > a";
    public static String birthDate = "span.ic-icon-regular.ic-icon-calendar";
    public static String cate1 = "Skin Care";
    public static String cate2 = "Beauty";
    public static String cate3 ="Skin";
    public static String pr1 = "BioSilver";
    public static String pr2 ="BEL & BELLE HAIR BATH CREAM";
    public static String pr3 ="Corporate Brochure 5 Pack ENG US";
    public static String pr4 ="EN43 Prod";
    public static String pr5 ="EN43 Prod";
    
    public static String taxon1 = "Products";
    public static String taxon2 = "Tools";
    public static String taxon3 = "Events";
    public static String cityName = "New York";
    public static String autoSearch = "div > div > div > input#search";
    public static String revProdHeader = "#label-for-show_product_details_modal";
    public static String revProductHeaderText = "Review Product";
    public static String addAutoBtn = "#show_product_details_modal > div > div > div.modal-body > form > div > div > div > input[type='submit']";
    public static String addSchd = "#show_product_details_modal > div > div.modal-content > div.modal-body > form > div:nth-child(3) > div:nth-child(2) > div:nth-child(4) > div > input[type='submit']:nth-child(4)";
    public static String moreIconAutoship = "form > div > div >  div > div > div.number-spinner-more";
    public static String fNameAs= "input#autoship_ship_address_attributes_firstname";
    public static String lNameAs= "#autoship_ship_address_attributes_lastname";
    public static String street1AS= "#autoship_ship_address_attributes_address1";
    public static String street2AS= "#autoship_ship_address_attributes_address2";
    public static String cityAS= "#autoship_ship_address_attributes_city";
    public static String phoneAS= "#autoship_ship_address_attributes_phone";
    public static String stateAS= "select#autoship_ship_address_attributes_state_id";
    public static String zipCodeAS= "#autoship_ship_address_attributes_zipcode";
    public static String countryAS = "#autoship_ship_address_attributes_country_id";    
    public static String CCName = "div.form-group.string.required.order_payment_source_name > input#name_on_card_4";
    public static String CCNumber = "input#card_number";
    public static String CCExpire = "input#card_expiry";
    public static String CCCardCode = "input#card_code";
    public static String CCCardNo = "378282246310005"; // 371449635398431   378734493671000  6011111111111117
    
    
    public static String MCCName = "div.form-group.string.required.order_payment_source_name > input#name_on_card_1";    
    public static String paymentOptions ="#checkout_form_payment > div.card_options > input";
    public static String useExsitingCardRadio = "#use_existing_card_yes";
    public static String newCardRadio = "#checkout_form_payment > div.card_options > label:nth-child(5)";
    public static String payOp = "//*[@id='checkout_form_payment']/div[1]/label[2]";
    public static String autoID = "div.panel-heading > div.row > div.col-md-3 > div:nth-child(1)";
    public static String addOptions ="#shipping_address_entry_options > input";
    public static String prodImage= "div#products.row > div > div.panel.panel-product > div.panel-heading > a > img";
    public static String prodNameInWishlist = "#line_items > tr > td:nth-child(3)";
    public static String autoProdName = "#main-content > div > div > div > div > div > div.panel-body > div > div > div.pull-left.item-name > h4 > a";
    public static String goBackToStore = "Go Back To Store";
    public static String taxonList = "#taxonomies > ul > li > a";
    public static String ASEdit = "div.col-md-3 > div.actions >a.btn.btn-primary";
    public static String ordId = "#orders > table > tbody > tr.ohistory > td:nth-child(1)";
    
    public static String ordIds1 = "#orders > table > tbody:nth-child(";
    public static String ordIds2 = ") > tr.ohistory > td:nth-child(1)";
    public static String prod1 = "#orders > table > tbody:nth-child(";
    public static String prod2 = ") > tr.order-history-items > td.col-md-10 > div > div > a:nth-child(3)";
    public static String pv1 = "#orders > table > tbody:nth-child(";
    public static String pv2 = ") > tr.ohistory > td:nth-child(3)";
    public static String tot1 = "#orders > table > tbody:nth-child(";
    public static String tot2 = ") > tr.ohistory > td:nth-child(4)";
    public static String  auotItems = "div > table.table > tbody > tr.ohistory > td:nth-child(1)";
    public static String taxonSearch = "#sidebar_products_search > input.btn.btn-primary";
    public static String pricRang= "#sidebar_products_search > div > ul > li > label";
    public static String conPricIcon = "#product-price > div > table > tbody > tr.w-price > th > a > i.fa.fa-question-circle";
    public static String cpHead = "h1#label-for-consultantPriceModal";
    public static String cpClose = "#consultantPriceModal > div > div.modal-content > div.modal-footer > a.btn.btn-default";
    public static String  cpEnroll= "#consultantPriceModal > div > div.modal-content > div.modal-footer > a.btn.btn-primary.product-enroll-link";
    public static String cpHeadText = "Consultant Price"; 
    public static String contList = "div.col-md-2.text-center>a>h3";
    public static String country1 = "United States";
    public static String country2 = "Canada";
    public static String packSize= "div.form-group.string.optional.pyr_core_v2_enrollment_sk_sku > div.starter-kits-table-container > div > div.col-md-6.starter-kit-description > h3";
    public static String pacName1= "div.form-group.string.optional.pyr_core_v2_enrollment_sk_sku > div.starter-kits-table-container > div:nth-child(";
    public static String pacName2= ") > div.col-md-6.starter-kit-description > h3";   
    public static String pacSelect1= "div.form-group.string.optional.pyr_core_v2_enrollment_sk_sku > div.starter-kits-table-container > div:nth-child(";
    public static String pacSelect2= ") > div.col-md-3.pricing > div > label";  
    public static String searchNews = "#q_title_or_excerpt_or_content_cont";
    public static String srcIcon = "#pyr_core\2f company_news_search > div > div > button > i";
    
    public static String  FirstName_text ="tulasi"+TestBase.generateRandomNumberInRange(10, 1000); 
    public static String  LastName_text ="ravi"; 
    public static String  SSN ="15964"+TestBase.generateRandomNumberInRange(1000,9999);
    public static String  Phone_text ="234523"+TestBase.generateRandomNumberInRange(1000,9999);
    public static String  Email_text ="tulasi.ravi@icentris.com";    

    public static String  Addr1_text ="Sf1, Floor -"+ TestBase.generateRandomNumberInRange(10,30);
    public static String  Addr2_text = TestBase.generateRandomNumberInRange(10,99)+" Avenue"; 
    public static String  City_text="Panama City";
    public static String  State_text ="Florida";
    public static String  PostalCode_text ="32401"; 
    public static String packsText1 = "Silver Enrollment Pack 142CV (ESP)";
    public static String packsText2 = "Starter Kit US (ESP)";
    public static String packsText3 = "Starter Kit US";
    public static String packsText4 ="Gold Enrollment Pack 398CV (ESP)";
    public static String itemDes = "#products-table > tbody > tr > td:nth-child(1) > a"; 
    public static String record = "#main-content > div";  //> div.panel-body > div.filter-and-sort
    
    
    public static String   addCreditbtn="#payment_option_credit > a"; 
    public static String   eCardHolder="#pyr_core_v2_enrollment_tmp_credit_card_card_name";
    public static String   eCardNo="#pyr_core_v2_enrollment_tmp_credit_card_card_number";
    public static String   eCardCvv="#pyr_core_v2_enrollment_tmp_credit_card_card_cvv"; 
    public static String   eExpireMon="select#pyr_core_v2_enrollment_tmp_credit_card_card_expire_month";
    public static String   eExpireYr="select#pyr_core_v2_enrollment_tmp_credit_card_card_expire_year"; 
    public static String   eCardSave="section > div:nth-child(4) > div > a";
    public static String selectAddres = "div.row > div > div > div > div:nth-child(1) > a";
    public static String ccNoText = "371449635398431";
    public static String  upsAuto="#pyr_core_v2_enrollment_shipping_method_12";
    public static String  ups ="#pyr_core_v2_enrollment_shipping_method_1"; 
    public static String confirmSubmitbtn = "Confirm";
    public static String emailAlert = "can't be blank and is invalid";
    public static String countryAlert = "Country and State are required.";
    public static String custAlertText = "can't be blank";    
    public static String eCardParentCatDir="div > ul.panel-nav.categories > li > a";  //:nth-child(2)"; > a:nth-child(2
    public static String eCardParentCatDirBfr ="div > ul.panel-nav.categories > li:nth-child(";
    public static String eCardParentCatDirAft =") > a";
    
    
    //Notifications
    public static String toolsTaskText = "User Task";
    public static String toolsMsgText = "Message";
    public static String toolsProfileText = "Profile";
    public static String toolsResourceText = "Resource";
    public static String toolsMsgRecipient = "Message Recipient";
    public static String msgText2 = "Message from ";
    public static String recipient  = "tulasi.ravi@icentris.com";
    public static String notiIcon = "#special-menu > li:nth-child(1) > a > i";
    public static String viewAllNoti= "#special-menu > li.open > ul > li > a";
    public static String viewAllNoti1= "#special-menu > li.open > ul > li > a";
    public static String notSub = "div > input#email_message_subject";
    public static String toolsPane= "div >  ul#filter-list > li > a"; //"#main-content > div > div.panel-column.col-md-2 > div:nth-child(1) > ul > li > a";
    public static String prityList="div.panel-column-group > ul#priority-list > li";  //"#main-content > div > div.panel-column.col-md-2 > div.panel-column-group.priority-list > ul > li";
    public static String prityList1 = "div.panel-column-group > ul#priority-list > li:nth-child(";
    public static String prityList2 = ") > a";
    public static String mailNotiBr= "div#navbar-collapsable > ul#special-menu > li > a > i.ic-icon-regular.ic-icon-inbox";
    public static String mailNotiMaster= "div.btr.ic-icon-inbox";//"div.btr.ic-icon-inbox";//"#special-menu > li:nth-child(2) > a > i";
    public static String mailcount= "#special-menu > li.open > ul > li:nth-child(11) > a";//"#special-menu > li:nth-child(2) > a > div";
    public static String alertCount= "#special-menu > li:nth-child(1) > a > div";
    public static String panelCont= "#main-content > div > div.panel-content.col-md-10.notifications-list";
    public static String paContMsg= "#main-content > div > div > div.notifications-list > div > div > div > div > a > div"; //"#main-content > div > div.panel-content.col-md-10.notifications-list > div > div > div > div > a > div"; // > div.media-heading";
    public static String paContMsg1 ="#main-content > div > div > div.notifications-list > div:nth-child(";
    public static String paContMsg2 = ") > div > div > div > a > div > div.media-heading";
    public static String paContSub = "#main-content > div > div > div.notifications-list > div > div > div > div > a > div > div:nth-child(1)";
    public static String viewMail = "#label-for-viewing-email-modal";
    public static String reply = "#email-panel > div > div.email-reply-forward-controls > a:nth-child(1)";
    public static String toMail ="div > input#recipients-to";   //"div > div.controls.manifest_list_container.cc_container_to > div > input#recipients-to";
    public static String sendMail="#email_message_buttons > a.btn.btn-primary.btn-sm";
    public static String AllCount =   "//*[@id='main-content']/div/div[2]/div[1]/ul/li[1]/div/div";//"div > ul > li.active > div > div.badge";
    public static String panelTitle = "#main-content > div > div.panel-heading > div.panel-title";
    public static String ReplyMail = "i.ic-icon-regular.ic-icon-reply";
    public static String ReplyAll = "i.ic-icon-regular.ic-icon-reply-all";
    public static String forwardMail = "i.ic-icon-regular.ic-icon-forward";


    public static String panelTitleText1 ="Notifications - Message Recipients";
    public static String panelTitleText2 ="Notifications - Business";	

  public static String prAll = "All";
 	public static String prLo = "Low";
 	public static String prMed = "Medium";
 	public static String prHi = "High";
 	public static String prUrgnt = "Urgent";
 	public static String prCrit = "Critical";
 	public static String NoNoti = "#main-content > div > div.panel-content.col-md-10.notifications-list > h4";
 	public static String NoNotiText = "You Don't Have Any Notifications";
  
    // public static String autoSearch = "div > div > div > input#search";
    // public static String custFN = "";
    
    
    // **************************** Business -> MyWebsite *****************************************************
    
   
  
 	public static String  panelActiveMyWebsites = "//*[@id='active_websites']"; // "#main-content > div > div > div > #websites > div > div#active_websites";  // ""; 
    public static String  panelAvailableTemplates = "//*[@id='available_templates']"; // "#main-content > div > div > div.tabbed-content > div#websites.tab-pane.active > div.tab-content > div#available_templates";
    public static String  inputWebsiteName = "//*[@id='pyr_pwp_site_name']";
    public static String  btnAddSite = "//input[@label='Add Site']";
    public static String availableTemp = "//*[@id='available_templates']/div";
    
    public static String  websiteName_text =("vibs-"  + TestBase.random()).trim();
    
    // master locators
    
    public static String inputWebOwnerName=  "#pyr_pwp_site_site_user_editables_attributes_1_content";
    public static String inputWebOwnerPhone= "#pyr_pwp_site_site_user_editables_attributes_2_content";
    public static String btnWebsiteSave= "div.row > div:nth-child(3) > input";
    // public static String btnCloseWebsite = "#page > div.workspace-modal.modal.fade.in > div > div > div.col-md-3 > div.modal-header  > div:nth-child(2)  > div.close-container > button > i"; 
    //  public static String btnCloseWebsite = "#page > div.workspace-modal.modal.fade.in > div > div > div.modal-header > div > div:nth-child(3) > div > div > button > i";  // monat
    
    
    public static String txtWebOwnerPhone = "8828282828";
    public static String webUrl = "#page > div.workspace-modal.modal.fade.in > div > div > div.col-md-9.content-box > div.modal-header> div > div > div.modal-pagination > div";
    
 	
  //************************* CHAT > PEOPLE  **********************************************************/
      
    public static String panelListPeople = "//*[@id='community-tabs']/div/div[2]";
    											// "//*[@id='people_list']/div/div";    
   
    
    
  //************************* Production TestData  **********************************************************/
    
    // Business -> Contacts 
       
       public static String  contactFirstNameProd_text = "Bill";
       public static String contactLastNameProd_text = "Gates";
       public static String contactEmailProd_text = "bill_gates@icentris.com";
       public static String deleteContact = "#contact_detail > div > div > div > div > ul > li:nth-child(2) > a";
       public static String toggleContactDetails = "#contact_detail > div > div > div > div > button.btn.btn-default.dropdown-toggle";
       
    // Marketing -> RL 
       
       public static String  parentCategory4Prod_text = "TupperWare Product_584";
       public static String newResourceTitle4Prod_text = "Bloosom Mugs698";
   
    
    // Marketing -> RL 
       public static String autoITProfilePath = projectPath + "\\testdata\\auto-it\\profileUpload.exe";
       public static String btnBrowseFileSave = "//*[@id='updateProfilePhotoModal']/div/div[1]/div[3]/button";
       public static String btnBrowseProfileSubmit = "div.modal-content > div.modal-body > form.edit_pyr_core_profile > input.btn.btn-primary.pull-right";
       public static String imgProfile = "//*[@id='page-header']/header/div/div[1]/div[1]/img";
       public static String imgProfile_text  = "Healthy drinks";
       
       public static String previewHeader ="div.modal-title";    //"//*[@class='modal-title']";
       
     
     // Users -> Shopping
       
       public static String btnAddToCart = "//*[@id='add-to-cart-button']";
       public static String testProductProduct1_text = "Appetite Chew";
       
       public static String tblWishlist = "//*[@id='line_items']";
       
       // Communication -> Inbox
       
       public static String inputGmailPasswd1 = "#Passwd";
       
       // Business -> Tasks
       
       public static String panelTasksIncomplete = "#tasks_incomplete";  // #tasks_incomplete2 > div > table > tbody
       public static String panelTasksFuture = "#tasks_future";
       public static String panelTasksOverdue = "#tasks_overdue";
       public static String panelTasksToday = "#tasks_due_today";
       public static String panelTasksCompleted = "#tasks_completed";
       public static String panelTasksNoDueDate = "#tasks_no_due_date";
       
       public static String inputTaskDuedate = "//*[@id='pyr_core_user_task_due_date']";
       public static String taskIncomplete = "Task_Incomplete_" +  TestBase.random();
       public static String taskComplete = "Task_Complete_" +  TestBase.random();
       public static String taskFuture = "Task_Future_"+TestBase.random();
       public static String taskToday = "Task_Today_"+TestBase.random();
       public static String taskOverdue = "Task_Overdue_"+TestBase.random();
       public static String taskDuedate_text = TestBase.getDate(2,"MM/dd/yyyy");
       public static String btnMarkAsComplete = "//*[@id='task-detail-modal']/div/div[1]/div[2]/div[2]/div/div[1]/ul/li[2]/label";
       
       public static String calendarDatePicker = "//*[@id='new_pyr_core_user_task']/span/div/div[1]/div/div[2]/span/button";
       public static String btnCalendarNext = "html/body/div[2]/div/div[1]/table/thead/tr[1]/th[3]";
       public static String btnCalendarPrev = "html/body/div[2]/div/div[1]/table/thead/tr[1]/th[1]";
       public static String calendarWidget = "html/body/div[2]/div/div[1]/table/tbody";
       
       // Tasks -> New Tasks widget
       
       public static String inputTaskActionItem = "#pyr_core_user_task_actionable_item";
       public static String taskActionItem_text = "Sales Demo";
       
       
       public static String bulkSubject ="input#subject";   //"input#email_message_subject";   //"input#subject";
       public static String bulkMsg = "textarea#message";
       public static String bulkSendEMail = "div#send-bulk-email-modal > div > div.modal-content > div.modal-body > form > div > input.btn.btn-primary";
       public static String bulkSelectAll = "input#check_all_emails_";
       public static String EventText = "Discussion - How to Generate "+TestBase.generateRandomNumberInRange(10, 20)+ " Reports" ;
       public static String sortLevel = "Level";
       public static String sortconstId = "Consultant ID";
       public static String sortFName = "First name";
       public static String sortLName = "Last name";
       public static String sortRanker = "Rank";
       public static String subjectBulk = "Success and Failure has " +TestBase.generateRandomNumberInRange(10, 20) +" diffence." ;
       public static String msgText = "Coming together is a beginning. Keeping together is progress. Working together is success.";
       public static String adEvent = "#create-bulk-event-form > a";
       public static String evName = "#pyr_crm_event_title";
       public static String sDate = "#pyr_crm_event_starts_at";
       public static String eDate = "#pyr_crm_event_ends_at";
       public static String eventSave ="#submit-form";
       public static String sendInv = "#create-bulk-event-form > input.btn.btn-primary";
       public static String eventID = "#event_id";
       public static String sub = "input#subject";
       public static String eventbody = "textarea#body";
       
       public static String tabContent = "#report_results > thead > tr > th";
       public static String dataTable = "#report_results > tbody:nth-child(2) > tr > td:nth-child(4)";
       public static String col1 = "#report_results > thead > tr > th:nth-child(";
       public static String col2 = ") > a";
       public static String result = "#page_entries_info > span";
       public static String treRank = "#rank_highlight";
       public static String treNode = "#highlighted_nodes";
       public static String treHiLightRadio = "#tree_form_container > label";
       public static String treEmail = "#tree_form_container > a";
       public static String treLevDep = "select#search_params_no_of_levels";
       public static String treDownSource = "select#search_params_downline_source";
       public static String treType = "select#search_params_type";
       public static String trecontId = "input#search_params_tree_consultant_search";
       public static String treSubmit = "#tree_form_container > form > div.row > div:nth-child(1) > a";
       public static String treMsg = "Send Message";
       public static String treProfile = "View Profile";
       public static String treeFollow = "Follow";
       public static String treImg = "div#tree-div > svg#tree-svg";      
       public static String abuseList = "#abuse > table > tbody > tr > td:nth-child(1) > div:nth-child(2) > a";
       
       public static String abList1= "#abuse > table > tbody > tr:nth-child(";
       public static String abList2 = ") > td:nth-child(1) > div:nth-child(2) > a";      
       public static String abRestore1 ="#abuse > table > tbody > tr:nth-child(";
       public static String abRestore2 =") > td:nth-child(6) > a:nth-child(2) > i";
       public static String abDel1 ="#abuse > table > tbody > tr:nth-child(";
       public static String abDel2 =") > td:nth-child(6) > a:nth-child(1) > i";
       
       public static String eConfigTitleText = "Email Configuration";
       public static String eConfigTitle = "#label-for-email_modal";
       public static String eSub = "div.modal-body > div#email > form.simple_form > div > input#_subject";
       public static String eReason = "textarea#_reason";
       public static String eSendMail = "#email > form > div.actions > input";
       
       
       // YouTube ******************************************************/
       
       public static String masDistlogin = "ganga";      //"raj.mpro1" ramesh.buridi  raj.19pro1
       public static String masDistPswd = "password1";
   	   public static String buridi = "buridir";
   	   public static String buridiPwd = "password2";      
       public static String youtubeTitle = "YouTube Trainings "+TestBase.generateRandomNumberInRange(10, 150);
       public static String youtubeTitleUpdated = youtubeTitle+" updated";
       public static String youtubeDesc = "Describes with "+TestBase.generateRandomString();
       public static String addUtube = "div.panel-heading > div.panel-tools > a";
       public static String pageTitle = "VIBE Master";
       public static String btnUploadYoutubeVideo = "input#pyr_community_video_upload";
       public static String btnUploadVideo = "input#upload_video";
       public static String UTitle = "input#pyr_community_video_title";
       public static String UDesc = "textarea#pyr_community_video_description";
       public static String Usave = "input#submit_video";
       public static String  UContinue  = "input#continue_video";    
       public static String UUpdate = "#main-content > table > tbody > tr > td.last > a:nth-child(1)";
       public static String UDelete = ") > td.last > a:nth-child(2)";       
       public static String comDeleteOk = "div.modal-dialog > div > div > button.btn.btn-primary";
       public static String tubeList = "tbody > tr > td:nth-child(2)";
       public static String tubeListBfr = "tbody > tr:nth-child(";
       public static String tubeListAfr =") > td:nth-child(2)";
       public static String tubedescAfr =") > td:nth-child(3)";
       public static String tubeEditAfr =") > td.last > a:nth-child(1)";
       public static String tubeDelAfr =") > td.last > a:nth-child(2)";
       public static String tubeListTitle = "div.panel-title";
       public static String tubeListTitleText = "Upload Videos To Youtube";
      

       // *************  Vibe : Profile dropdown -> Notifications page  *********************************/
       
       public static String paneNotifications = "//*[@id='messaging']/div[1]/table/tbody";
       

     // Training
   	
   	public static String tableTrainingCategories = "training_categories_panel_list";
   	public static String inputCategoryName = "pyr_core_training_category_category_name";
   	public static String trainingCategory = "Training Category "+TestBase.generateRandomNumberInRange(10, 1000);
   	public static String categoryName = "Test Category "+TestBase.generateRandomNumberInRange(10, 1000);
   	public static String categoryName2 = "Test2 Category "+TestBase.generateRandomNumberInRange(10, 1000);
   	public static String rankDef = "//*[@id='access_profile_settings']/div[1]/div[1]/div";
   	public static String marketLang = "//*[@id='access_profile_settings']/div[2]/div[1]/div";
   	public static String subscriptionPlan = "//*[@id='access_profile_settings']/div[3]/div[1]/div";

   	

   	public static String btnCreateTrainingCategory = "//*[@id='new_pyr_core_training_category']/div/div[1]/div[2]/input";
   	/*public static String btnCreateTrainingCategory = "//*[@id='new_pyr_core_training_category']/div[2]/input";    // moab
*/   	
   	public static String btnAddTrainingCategory = "Add Training Category";
   	
   	public static String btnAddSeries="//*[@id='main-content']/div[2]/div/a";
   	public static String trainingSeriesName="//*[@id='pyr_core_training_series_name']";
   	public static String trainingSeriesDesc="//*[@id='pyr_core_training_series_description']";
   	public static String trainingSeriesDependency="//*[@id='pyr_core_training_series_dependency']";
   	public static String createTrainingSeries="//*[@id='new_pyr_core_training_series']/div[3]/input";
   	
	public static String trainingSeries = "Training Series "+TestBase.generateRandomNumberInRange(10, 1000);
   	public static String seriesName1 = "Test Series "+TestBase.generateRandomNumberInRange(10, 1000);
   	public static String seriesName2 = "Test Series "+TestBase.generateRandomNumberInRange(10, 1000);
   	public static String seriesName3 = "Test Series "+TestBase.generateRandomNumberInRange(10, 1000);
   	public static String seriesDesc1 = seriesName1 + " description.";
   	public static String seriesDesc2 = seriesName2 + " description.";
   	public static String seriesDesc3 = seriesName3 + " description.";
   	
   	
   	public static String tableSeries = "training_series_panel_list";
   	public static String btnAddTraining = "//*[@id='training_videos_list']/div/div/div/a";
   	public static String inputAddVideoTitile = "//*[@id='pyr_core_training_video_title']";
   	public static String inputAddVideoDesc = "//*[@id='pyr_core_training_video_description']";
   	public static String chkPublishArticle = "#publish_now";
   	public static String selectFileType = "//*[@id='pyr_core_training_video_file_type']";
   	public static String selectFilePath = "#pyr_core_training_video_file_path";
   	
   //	public static String btnCreateTrainingVideo = "//*[@id='new_pyr_core_training_video']/div/div[1]/div[13]/input[2]";   // TUP 
   		//"//*[@id='new_pyr_core_training_video']/div[3]/input[2]"; // need to change this div[3] to div[2] only on master,other environments it works fine.
  //    public static String btnCreateTrainingVideo = "//*[@id='new_pyr_core_training_video']/div/div[1]/div[12]/input[2]";  // master
   	
   	public static String trainingAssetTitle = "Training Asset " + TestBase.generateRandomNumberInRange(10, 1000);
   	public static String trainingTitle = "Test Training " + TestBase.generateRandomNumberInRange(10, 1000);
   	public static String trainingTitle2 = "Test Training " + TestBase.generateRandomNumberInRange(10, 1000);
   	public static String trainingTitle3 = "Test Training " + TestBase.generateRandomNumberInRange(10, 1000);
   	public static String trainingDesc = trainingTitle + " description";
   	public static String trainingDesc2 = trainingTitle2 + " description.";
   	public static String trainingDesc3 = trainingTitle3 + " description.";
   	public static String fileType = "PDF";
   	public static String uploadPdfPath1 = projectPath +  "\\testdata\\auto-it\\training_PDF.exe";  
    public static String backToSeries = "//*[@id='main-content']/ol/li[2]/a";
   	public static String back2ListOfSeries = "//*[@id='main-content']/ol/li[1]/a";
   	public static String trainingDependency = "//*[@id='pyr_core_training_video_training_video_property_attributes_dependency']";
   	public static String tableTrainings = "training_videos_panel_list";
   	
   	public static String categoryPanel = "//*[@id='main-content']/div/div[2]/ul";
   	public static String seriesPanel = "//*[@id='main-content']/div/div[3]/div[1]";
   	public static String listSeries = "//*[@id='main-content']/div/div[3]/div[1]/div";
   	public static String closeTrainingWindow = "//*[@id='page']/div[7]/div/div/div[2]/div[1]/div[2]/div/button";
   	
   	 // ***************** Admin  >> Broken Links in Admin Sidebar *******************************//
     
     public static String  adminNavigationbar = "#navbar-admin-container.col-md-2";
     public static String  adminNavigationGroups = "div.accordion-group > div.accordion-heading";
     public static String  adminInnerPane = "//div[@class='accordion-group']/div[@class='accordion-body collapse']/div[@class='accordion-inner']/ul";

     
     /* ************************** Shopping -> Autoship ************************************************************************************************* */
     
     // SELECT MARKET dialog

     public static String dialogSelectMarket = "//*[@id='market_selection_modal_popup']/div/div[1]";
     public static String drpDnSelectMarket = "//*[@id='market']";
     public static String btnSelectMarketOK = "//*[@id='market_selection_modal_popup']/div/div[1]/div[3]/button";

     // Login to Shopping

     public static String inputShopUserLogin = "//*[@id='user_login']";
     public static String inputShopUserPasswd = "//*[@id='user_password']";
     public static String btnShopUserLogin = "//*[@id='new_user']/div[3]/div/input";

     // Search product with category and SKU.
    
     public static String inputSearchProduct = "//*[@id='search']";
     
     public static String inputSearchProductNameSKU = "//*[@id='keywords']";
     public static String drpdnSelectProductCategory = "//*[@id='taxon']";		 
     public static String drpdnProductSortBy = "//*[@id='sort_products_value']";
     
     public static String selectProductCategory_text = "Products";
     public static String selectProductNameSKU_text = "Vitamin Coffee 30 Pack US ";
     public static String paneSelectProduct = "//*[@id='products']";
     public static String productSortBy_text = "Price Low";
     public static String btnAddtoAutoship = "//*[@id='add-to-autoship-button_on_page']";
     public static String btnAutoship = "//*[@id='checkout-link']"; 
   
     
    // Autoship - Add new shipping details 
     
     public static String radioUseNewAddr = "//*[@id='use_existing_shipping_address_no']";
     public static String inputOrderShipAddrFName = "//*[@id='order_ship_address_attributes_firstname']";
     public static String inputOrderShipAddrLName = "//*[@id='order_ship_address_attributes_lastname']";
     public static String inputOrderShipAddr1 = "//*[@id='order_ship_address_attributes_address1']";
     public static String inputOrderShipAddr2 = "//*[@id='order_ship_address_attributes_address2']";
     public static String inputOrderShipCity = "//*[@id='order_ship_address_attributes_city']";
     public static String drpdnOrderShipCtry = "//*[@id='order_ship_address_attributes_country_id']";
     public static String drpdnOrderShipState = "//*[@id='order_ship_address_attributes_state_id']";
     public static String inputOrderShipZipcode = "//*[@id='order_ship_address_attributes_zipcode']";
     public static String paneShippingAddr = "//*[@id='collapseAddress']/div";
     
     public static String orderShipAddrFName_text = "jayadev";
     public static String orderShipAddrLName_text = "mootha";
     public static String orderShipAddr1_text = "College Eight Road";
     public static String orderShipAddr2_text = "Santa Cruz";
     public static String orderShipCity_text = "CA";
     public static String orderShipCtry_text = "United States of America";
     public static String orderShipState_text = "California";
     public static String orderShipZipcode_text = "90000";
    
     public static String btnOrdershipSaveandContinue = "//*[@id='checkout_form_address']/div[2]/input";
     
     
     // Autoship - select existing address
     
     public static String panelShipping = "//*[@id='shipping']";
     public static String radioSelectExistingAddress = "//*[@id='use_existing_shipping_address_yes']";
   
  // Autoship - select delivery
     
     public static String btnDeliverySaveandContinue = "//*[@id='checkout_form_delivery']/div[2]/input";

  // Autoship -  new payment details
        
     public static String radioNewPaymentDetails = "//*[@id='use_existing_card_no']";
     public static String radioSelectPaymentMethod = "//*[@id='order_payments_attributes__payment_method_id_3']";
     public static String inputPaymentCardName = "//*[@id='name_on_card_3']";
     public static String inputPaymentCardNo = "//*[@id='card_number']";
     public static String inputPaymentExpiry = "//*[@id='card_expiry']";
     public static String inputPaymentCardcode = "//*[@id='card_code']";
     public static String chkUseSameShippingAddr = "//*[@id='use_shipping_address_for_billing']";	// billing and shipping addr
     
     public static String paymentCardName_text = "Jayadev M";
     public static String paymentCardNo_text = "4012888888881881";
     public static String paymentExpiry_text = "08/19";
     public static String paymentCardcode_text = "569";
          
     
  // Autoship -  existing card details
     
     public static String radioSelectExistingCard = "//*[@id='use_existing_card_yes']";
     public static String btnPaymentSaveandContinue = "//*[@id='checkout_form_payment']/div[2]/input";
     
     
  // Autoship - Confirm page
     
     public static String  btnPlaceOrder = "//*[@id='order_details']/div[4]/input";
     
  // Autoship - Edit Autoship page
     
     public static String btnSelectProductNext = "//*[@id='current_products_in_autoship']/div/a";
    
  // AUtoship - New Billing address (in Summary page)
     
     public static String paneBilling = "//*[@id='autoship_details']/div[2]";

   
     //  User Specific Tests : Community
     
     public static String panelMyRecentActivity = "div#profile-timeline.panel.panel-widget > div.panel-body"; 
     
     public static String addPrivatePostTitle_text = "Private_Post_"  + TestBase.random();
     public static String addPublicPostTitle_text = "Public_Post_"  + TestBase.random();
     public static String addCommunityPostTitle_text = "Community_Post_"  + TestBase.random();
     
     public static String addPrivatePhotoTitle_text = "Private_Photo_"  + TestBase.random();
     public static String addPublicPhotoTitle_text = "Public_Photo_"  + TestBase.random();
     public static String addCommunityPhotoTitle_text = "Community_Photo_"  + TestBase.random();
    
     public static String addPrivateVideoTitle_text = "Private_Video_"  + TestBase.random();
     public static String addPublicVideoTitle_text = "Public_Video_"  + TestBase.random();
     public static String addCommunityVideoTitle_text = "Community_Video_"  + TestBase.random();
    
     
     public static String postText = "What's on your mind?";
     public static String ComHeaderText = "What's Going On In The Community?";    
     
     public static String commTitle =  "textarea#pyr_community_status_status.text.required.auto-resize.form-control";
     public static String comHeader = "#widget-recent-activity > div.panel-heading > div.panel-title";
     public static String comPhoto = "//*[@id='widget-recent-activity']/div[2]/div[1]/a[1]";   //"div.panel-body > div > a:nth-child(1)";
     public static String comBlog = " //*[@id='widget-recent-activity']/div[2]/div[1]/a[2]";  //"div.panel-body > div > a:nth-child(2)";// #status-box > div.panel-body > div > a:nth-child(2)";
     public static String fbPwd = "input#pass";
     public static String fbLogBtn = "input#u_0_1";
     public static String fbPostTitle = "span > div > div > div:nth-child(1)"; 
     public static String fbPost =  "div > div.lfloat._ohe > span > div:nth-child(2)";
     public static String fbPost2ndPart = "div > div._42ef > span > div:nth-child(1) >div > div:nth-child(1) > a"; 
     public static String fbLogout = "//*[@id='BLUE_BAR_ID_DO_NOT_USE']/div/div/div[1]/div/div/ul/li[12]/a/span/span";
     public static String activitiesBefore = "//div[@class='activities-stream']/div[";              //"//div[2]/div/div/div[2]/div/div[";//"html/body/div[1]/section[2]/div[2]/div[2]/div/div/div[1]/div/div[2]/div/div/div[2]/div/div[";
     public static String PhotoTitle_text3 = "Hello "+TestBase.generateRandomNumberInRange(20, 2000);
     public static String actMsg = "div.col-md-7.table-cell.activity-box > div > div > div > div";
     public static String editlnk  = "div.community_activity_action.like-comment-links > span:nth-child(4) > a";
     public static String titleLabel = "label.string.required.control-label";
     public static String edtVedio = "span > a";
     public static String imgs = "#user-activity-tabs > div.panel.panel-full > div.panel-body > div > div > div > a > img";
     public static String img = ".//*[@id='user-activity-tabs']/div[1]/div[2]/div/div[1]/div/a/img";
     public static String recipMailId = "test.mail"+TestBase.generateRandomNumberInRange(1, 5000)+"@gmail.com";
     public static String subText =  "Awsome photo "+TestBase.generateRandomNumberInRange(1, 200);
     public static String subText2 =  "Awsome attach "+TestBase.generateRandomNumberInRange(1, 200);
     public static String profileImg = "//*[@id='profile-dropdown']/div[1]/span/img";
     public static String liList ="#ozfeed > ol > li > div > div.side-article > div > div > a > h4"; 
     public static String liAcc = "#account-nav > ul > li.nav-item.account-settings-tab > a > img";
     public static String lipro = "#main-site-nav > ul > li:nth-child(2) > a";
     public static String liTitle = "Madan Kommi | Tester at iCentris | LinkedIn";
     public static String mailSub = "div > input#email_message_subject";
     public static String rpTab= ".table > tbody > tr > td > a";
     public static String rptitle1 = ".table>tbody>tr:nth-child(" ;
     public static String rptitle2 = ")>td:nth-child(1) > a";
     public static String consts = "section.constraints-section > h3";
     public static String photoList = "#user-activity-tabs > div.panel.panel-full > div.panel-body > div > div > span";
     public static String repTitle = "#main-content > div > div.panel-heading > div.panel-title";
     public static String alertMsg = "body > div.bootbox.modal.fade.bootbox-alert.in > div > div > div.modal-body > div";
     public static String alertMsgText =  "Please add";
     public static String alertMsgText2 =  "__{{Please add a recipient to your message.}}__";
     public static String alertInvalidMsgText = "Invalid email addresses, please correct: ";
     public static String alertAttachMsgText = "Please Attach A File";
     public static String invalidMailId = "tulasi" +TestBase.generateRandomNumberInRange(1, 20)+"@@gmail.com";
     public static String alrtOk = "body > div.bootbox.modal.fade.bootbox-alert.in > div > div > div.modal-footer > button";
     public static String emailClose = "#email-modal > div > div.modal-content > div.modal-header > button > i";
     public static String imgAlert = "form > div > span.help-block";
     public static String imgAlertText = "can't be blank";
     public static String imgHttpText  = "trying to download a file which is not served over HTTP";
     public static String closePhoto = "//*[@id='addPhotoModal']/div/div[1]/div[1]/button"; //"div > div > div.modal-content > div.modal-header > button.close > i.ic-icon-regular.ic-icon-close.";
     public static String commphoto = "Community Photo With No "+ TestBase.generateRandomNumberInRange(1, 20);
     public static String commPrivatePhoto = "Private Photo With No "+ TestBase.generateRandomNumberInRange(1, 20);
     public static String actList = "div.col-md-7.table-cell.activity-box > div > div > div > div.table-cell.text-cell";
     public static String vieMor =  "div > div.panel-footer > a";
     public static String commList = "div > div.activity-content > a";
     public static String viewMoreInComm =  "div#widget-recent-activity > div.panel-footer > a";
     public static String invalidUrl = "https://www.invalidYoutube.com/watch?v=dUDnku_wIbQ";
     public static String invalidUrlAlert = "form > div > ul > li";
     public static String invalidUrlTxt = "Invalid URL";
     public static String videoText = "United we stand, divided we fall by "+ TestBase.generateRandomNumberInRange(1, 256);
     public static String twitLogin = "div.row.user > label";
     public static String twitPswD = "#password";
     public static String socSiteClose =  "#addShareModal > div > div.modal-content > div.modal-header > button > i";
     public static String twtLogin = "//*[@id='doc']/div[1]/div/div[1]/div[2]/button";//"div.StreamsHero > div.StreamsHero-buttonContainer >button.Button.StreamsLogin.js-login";
     /*public static String twtUser = "div.LoginForm-input.LoginForm-username > input";
     public static String twtPwd = "div.LoginForm-input.LoginForm-password > input";*/
	 public static String twtUser = "form > fieldset > div:nth-child(2) > input";
	 public static String twtPwd = "form > fieldset > div:nth-child(3) > input";
     public static String twtTweet = "fieldset > input.button";
     public static String twtPosts = "li > div > div.content > div.js-tweet-text-container > p";
     public static String twtlog = "#user-dropdown-toggle";
     public static String twtLogout = "li#signout-button > button.dropdown-link";  //"Log out";
     public static String remember =  "div > div.subchck > label.t1-label.remember > input";
     public static String tweet = "#global-new-tweet-button > span.Icon.Icon--tweet.Icon--large";
	 public static String textMsg = "#page-container > div > div.signin-wrapper > h1";
     public static String pulish = "div.checkbox > label > input#pyr_community_post_published";
     public static String iconList = "#dream_board_list > li > a > img";
     public static String icontip = "div > h5";
     public static String attach = "#email_message_buttons > a:nth-child(1)";
     public static String attachResource = "#email_message_buttons > a:nth-child(2)";
     public static String attachResrc = "#email_message_buttons > a:nth-child(3)";
     public static String browseInBr = "#message-attach-file-field"; //"//*[@id='message-attach-file-field']";//"message-attach-file-field";
     public static String browseInMas = "#pyr_crm_message_file"; //"//*[@id='pyr_crm_message_file']";
     public static String foot = "div.modal-footer > button.btn.btn-default";
     public static String footCommMail = "div#email_message_buttons > a.btn.btn-default.btn-sm skip-dirty";
     public static String gmailComment = "#\3a 0\2e f"; //"div#:0.f.df.b-K.b-K-Xb.URaP8.editable";
     public static String gmailShare = "//tbody/tr/td[1]/div[1]";//".d-k-l.b-c.b-c-Ba.qy.jt.b-c-u";
     public static String resList =  "div.resource-checklist > div > div > div.col-md-12.resource-browser-toggle";
     public static String resListB4r =  "div.resource-checklist > div:nth-child(";
     public static String resListAfr =  ") > div > div";
     public static String resSelectCount = "span.selected_count";
     public static String saveResource = "div.modal-footer > button.btn.btn-primary";
     public static String filepath = "c:\\vibe\\downloads\\";
     public static String fbGoalPost ="div > div:nth-child(2) > span";
     
     

     /* *************  Vibe : Marketing -> ResourceLibrary 2.0  *********************************/
    
     public static String resourceAsset = "Test Asset " + TestBase.generateRandomNumberInRange(10, 1000);
     public static String assetTypePDF = "PDF";
     public static String resourceItemsHeader = "div.media-body > h4.media-heading";
   /*  public static String resourceItems = "div.media-body > p";*/
     public static String resourceItems = ".resource-title";
     public static String lstKeywordSearchItems = "//*[@id='keyword_search_report']/div[1]/table/tbody/tr";
     public static String resourceItemsInAdmin = ".resource-title";
     
     public static String resourceLinks = "//*[@id='page']/div[8]/div/div/div[1]/div/div[3]/div/div[1]/a"; // "//*[@id='page']/div[7]/div/div/div[1]/div/div[3]/div/div[1]/a";
     public static String txtDownload = "Download";
     public static String resourceActionsCnt = "//*[@id='page']/div[7]/div/div/div[2]/div[2]/div[2]/div[1]/section[2]/div/span";
     public static String actionDownload = "Downloads";
     public static String txtSearchResource = "//*[@id='resource_query']";
     public static String btnKeywordSearch = "//*[@id='main-content']/div/div[1]/div[2]/a[1]";
     public static String filterResourceTypes = "//*[@id='filter_by_container']/ul/li[@class='dropdown-option ']";
     public static String filterByPDF = "PDF";
     public static String ddlFilterType = "//*[@id='filter_by_container']";
     public static String btnListView = "//*[@id='resource-body-content']/div[1]/div[1]/div/div/div/div[2]/div/a/i";
     public static String listResources = "//*[@id='index_page']/div[1]/ul/li/ul"; // "//*[@id='index_page']/div[1]/table/tbody/tr"; branch-1-10
     public static String listResourceActions = "//*[contains(@id,'more')]/button";
     public static String previewResource = "//*[contains(@id,'more')]/ul/li[1]/a";
     public static String searchResourceText = "Bloosom";
     public static String searchResourceKeywordText = "Bloo";
     public static String searchTrainingAssetText = "Test"; 
     public static String lnkEmail = "//*[@id='page']/div[7]/div/div/div[1]/div/div[3]/div/div[1]/a[3]";
     public static String btnComposeEmailAsAttachment = "//*[@id='page']/div[7]/div/div/div[2]/div[2]/div[2]/div/section[1]/div/a";
     public static String btnComposeEmailMyWebsite = "//*[@id='page']/div[7]/div/div/div[2]/div[2]/div[2]/div/section[2]/div/a";
     public static String txtSubject =  "Test Subject "+TestBase.generateRandomNumberInRange(1, 200);
     public static String lnkClick = "//*[@id='email_message_display']/div/div[1]/a";
     public static String lstResAssets = "//*[@id='email_message_display']/div[2]/div";
     public static String lnkShare = "//*[@id='page']/div[7]/div/div/div[1]/div/div[3]/div/div[1]/a[2]";
     public static String txtShare2SocialNetworks = "//*[@id='page']/div[7]/div/div/div[2]/div[2]/div[2]/div/section[1]/h5";
     public static String txtShare2Community = "//*[@id='page']/div[7]/div/div/div[2]/div[2]/div[2]/div/section[2]/h5";
     public static String txtShare2Pwp = "//*[@id='page']/div[7]/div/div/div[2]/div[2]/div[2]/div/section[3]/h5";
     
     public static String txtAsset = "//*[@id='label-for-shareLinkModal']";
     public static String selectChildCategory = "li > ul > li > a";
     public static String resourceTagName = "Test Resource Tag";
     public static String resTagsList = "//*[@id='index_page']/div[2]/a";
     public static String postTitle = "Test Post "+ TestBase.generateRandomNumberInRange(10, 1000);
     public static String inputCommentAsset = "#pyr_core_resource_asset_comment_comment";
     public static String btnCommentAsset = "//*[@id='new_pyr_core_resource_asset_comment']/div/span/input";
     public static String lnkWriteReview = "//*[@id='page']/div[7]/div/div/div[2]/div[2]/div[2]/div[1]/section[3]/div/div[2]/a/span";
     public static String reviewTitle = "Test Review "+ TestBase.generateRandomNumberInRange(10, 1000);
     public static String reviewDesc = "Test Review Desc "+ TestBase.generateRandomNumberInRange(10, 1000);
     public static String inputReviewTitle = "//*[@id='pyr_core_review_title']";
     public static String inputReviewDesc = "//*[@id='pyr_core_review_review']";
     public static String btnSaveReview = "//*[@id='new_pyr_core_review']/div/div[2]/div/div/input";
     public static String panelReview = "//*[@id='page']/div[7]/div/div/div[2]/div[2]/div[2]/div[1]/div/ul/li[2]";
     public static String reviewsList = "//*[@id='reviews']/section/div/div";     
     public static String commentsList = "//*[@id='comments']/section/div[2]/div";
     
     
     public static String RLSocialText = "Share to Social Networks";
     public static String RLCommText = "Share to Community";
     public static String RLWebsiteText = "Link resource to my Website";
     public static String webShareText = "Share To My Website";
     public static String commentsText = "Rl shares "+TestBase.generateRandomNumberInRange(1000, 9000); ;
     public static String ccat = "li > ul > li > a";
     public static String statisItem = "div > span > span";
     public static String statisItem1 = "div > span:nth-child(";
     public static String statisItem2 = ") > span";
     public static String statisCount = "div.badge.badge-";
     public static String likesCount = "//*[@id='page']/div[7]/div/div/div[2]/div[2]/div[2]/div[1]/section[2]/div/span[3]/div";
     public static String RLLikes = "section.modal-sidebar-content-controls > div > div:nth-child(1)> a";
     public static String RLControls = "div > div.controls-container > a";
     public static String RLSocial = "div > section.share-to-social-networks.clearfix > h5";
     public static String FBBTn = "a > span > svg.at-icon.at-icon-facebook > a";
     public static String btnClose = "i.ic-icon-regular.ic-icon-close.";//"div.modal-content > div.modal-header.clearfix > button.close > i.ic-icon-regular.ic-icon-close";
     public static String TweetIcon = "a.addthis_button_twitter.at300b > span > svg > g > path";
     public static String RLComm = "div > section.share-to-community.clearfix > h5";
     public static String commShare = "div > section.share-to-community.clearfix > div > a";
     public static String comtsShare ="#new_pyr_crm_resource_library_comment_share > div:nth-child(4) > input";
     public static String sharesCount =  "div.badge.badge-4";
     public static String RLWebSite = "div > section.share-to-my-website.clearfix > h5";
     public static String pwpAssetShareTitle = "#label-for-showPwpLinkModal";
     public static String webShare = "div > section.share-to-my-website.clearfix > div > a";
     public static String webShareTitle =  "#label-for-showPwpLinkModal";
     public static String shareLinktext = "#showPwpLinkModal > div > div.modal-content > div.modal-body > textarea";
     public static String viewWebsite = "#showPwpLinkModal > div > div.modal-content > div.modal-footer > a";
     public static String btnViewWebSite = "//*[@id='showPwpLinkModal']/div/div[1]/div[3]/a";
     public static String link_Text = "Email Link To My Website";
     public static String attach_Text = "Email As An Attachment";
     public static String linkText = "div > section > h5";
     public static String AttachText = "div > section.email-attachment.clearfix > h5";
     public static String attachCompBtn = "div > section.email-attachment.clearfix > div > a";
     public static String linkCompBtn = "div > section.email-a-link.clearfix > div > a";
     public static String esection = "div.modal-sidebar-content > div > section > h5";
     public static String RLfilter = "#filter_by_container > button > span.button-drop-down-display-text";
     public static String RLFiltOptions = "#filter_by_container > ul > li > a";
     public static String pageModal = "div.modal-header > div > div > div";
     public static String pagn = "div.modal-header > div > div > div > a";
     public static String fileTyp =  "section.resource-description > div:nth-child(3) > div.col-md-7";
     public static String inputAssetTitle = "input[id^='pyr_core_resource_resource_assets_attributes_'][id$='_title']";
     public static String drpdnAssetFileType = "select[id^='pyr_core_resource_resource_assets_attributes_'][id$='_file_type']";
     public static String textareaAssetFileDesc = "textarea[id^='pyr_core_resource_resource_assets_attributes_'][id$='_file_description']";
     public static String inputAssetPath = "input[id^='pyr_core_resource_resource_assets_attributes_'][id$='_file_path']";
     public static String btnAddAsset = "div.modal-footer > a:nth-child(1)";
  
     public static String inputBrowseThumbnail = "#pyr_core_resource_icon_path";
     public static String browseThumbnail_text = "Tag_" + TestBase.random();
     public static String tblManageCategories = "//div[@id='index_page']/div[2]";
     
     public static String divCategorieslist = "#categories_list > tbody";
     public static String divRankdeflist = "//*[@id='rank_definition_checkboxes']/div"; //"#rank_definition_checkboxes > div";
     public static String divMarketlist = "//*[@id='market_language_checkboxes']/div"; 
     public static String btnDeleteMultiple = "//*[@id='main-content']/div/div[1]/div[2]/a[1]";
     public static String divSubscriptionlist = "//*[@id='subscription_plan_checkboxes']/div"; 
     public static String confirmDeleteMulResources = "html/body/div[6]/div/div/div[3]/button[2]";
    // public static String[] uploadAssetlist = {"PDF","Image"}; // ,"Text"
     public static String[] rankdeflist = {"Consultant","Silver 2","Director"}; //for master it is Manager
     public static String[] idlRankDeflist = {"Senior Director"};
     public static String canadianMarket = "CA (English)";
     public static String assetType = "Resource Library";
     
    
     public static String inputExpiryDate = "#pyr_core_resource_expiry_date";
     public static String expiryDate_text = "03/29/2020";
     public static String btnCreateNewResource = "div.col-md-6 > input#pyr_core_resource_submit.btn.btn-primary";
     
    // public static String resourceItems = "div.media-body > p";
     
     public static String  parentCategory_text =  "PCAT_"  + TestBase.generateRandomNumberInRange(2, 900);   // "TupperWare Product_" + TestBase.generateRandomNumberInRange(2, 900);
     public static String  childCategory_text =  "CCAT_"  + TestBase.generateRandomNumberInRange(2, 900); 
     public static String  childCategory_text1 =  "CCAT_"  + TestBase.generateRandomNumberInRange(2, 900);
     public static String  updateParentCategoty_text = parentCategory_text + ".01";
     public static String  parentCat1 = "Summer Special "  + TestBase.generateRandomNumberInRange(500, 5000);
     public static String  childCat1 =  "Children products "  + TestBase.generateRandomNumberInRange(500, 9000);
     public static String newResourceTitle1 = "Vitamin B-"+ TestBase.generateRandomNumberInRange(200, 20000);
     public static String parentCategoryUpdated_text = parentCategory_text + "_Updated";
     public static String newResourceTitleUpdated_text = newResourceTitle_text + "_Updated";

     public static String newCloneResourceTitle_text = newResourceTitle_text + "_Cloned";
     public static String  parentCat =  "div.col-md-2.panel-column > ul > li > a";     
     public static String editWidExpand = "#widget_manager_home > div.widget-manager-header > div > div.btn-group > a:nth-child(2) > i";
     public static String backRL = "div > div.col-md-6.text-right > a:nth-child(2)";

     
     public static String parentCategory_text1 = "PCAT1_"  + TestBase.generateRandomNumberInRange(2, 900); 
     /*public static String childCategory_text1 = "CCAT1_"  + TestBase.generateRandomNumberInRange(2, 900);*/
     
     public static String parentCategory_text2 = "PCAT2_"  + TestBase.generateRandomNumberInRange(2, 900); //parent category without resources attached to it.
     public static String parentCategory_text3 = "PCAT3_"  + TestBase.generateRandomNumberInRange(2, 900);
     public static String canadianCategory_text1 = "PCAT1_Canadian_"  + TestBase.generateRandomNumberInRange(2, 900); 
     public static String childCategory_text2 = "CCAT2_"  + TestBase.generateRandomNumberInRange(2, 900); //child category without resources attached to it.
     public static String inputResourceTags = "//*[@id='pyr_core_resource_tag_list']";
     public static String resourcePanelCategory = "//*[@id='resource_library']/div/ul/li[@class!='active']";
     public static String assetTitle = "section.resource-description > div:nth-child(1) > div.col-md-7";
     public static String assetDescription = "section.resource-description > div:nth-child(2) > div.col-md-7";
     
     public static String btnBackResources = "//*[@id='index_page']/div[1]/div/div[2]/a[2]";
     public static String txtResourceHeading = "//*[@id='main-content']/div/div[1]/div[1]";
     public static String validateTitle = "//*[@id='resource_upload_assets']/div[1]/div[1]/span";
     public static String uploadVideoYoutube = "//*[contains(@id,'div_embed_code_')]/a";
     public static String listYoutubeVideos = "//*[@id='youtube-videos-create-modal']/div/div[1]/div[2]/table/tbody/tr/td[2]";
//     public static String linkYoutubeVideo = "//*[@id='youtube-videos-create-modal']/div/div[1]/div[2]/table/tbody/tr/td[1]/a";
     
     
     public static String publishDateValidation = "html/body/div[6]/div/div/div[1]/div";
     public static String txtPublishDateValidation = "Please Enter Publish Date";
     public static String[] resourceValidationMessages = {"Resource assets can't be blank","Title can't be blank","Category can't be blank"};
     public static String lnkEditAsset = "//*[@id='edit_icon_path_']/div[2]/div/a";
     public static String lstResourceValidations = "//*[contains(@id,'edit_pyr_core_resource')]/div[1]/ul/li";
     public static String btnValidateOK = "html/body/div[7]/div/div/div[2]/button";
     public static String btnPublishOK = "html/body/div[6]/div/div/div[2]/button";
     public static String lstAssets = "//*[contains(@id,'container')]/button";
     public static String txtValidateAssetName = "Please select file name first";
     public static String txtValidateAssetFileType = "Please select file type first";
     public static String txtValidateAssetFile = "Please upload file path to the asset";
     public static String txtValidatePDFFile = "Please select file extensions with .PDF";
     public static String validateAssets = ".bootbox-body";
     public static String editFilePath = "//div[contains(@id,'edit_file_path')]/div/a";
     public static String resTitleValidate = ".help-block";
     public static String txtResTitleValidation = "has already been taken";
     public static String lstResourceOptions = "//*[@id='main-content']/table/tbody/tr";
     public static String btnUpdateResourceOptions = "//*[@id='main-content']/table/tfoot/tr/td/input";
     public static String ddlResourceMarkets = "//*[@id='market_language_container']/button";
     public static String resCommentsTab = "//*[@id='page']/div[7]/div/div/div[2]/div[2]/div[2]/div[1]/div/ul/li[1]/a";
     public static String resShareOption = "//*[@id='page']/div[7]/div/div/div[1]/div/div[3]/div/div[1]/a[2]";
     public static String resReviewsTab = "//*[@id='page']/div[7]/div/div/div[2]/div[2]/div[2]/div[1]/div/ul/li[2]/a";
     public static String resLayout = "//*[@id='resource-body-content']/div[1]/div[1]/div/div/div/div[2]/div/a";
     public static String btnAttach2Message = "//*[@id='upload_file_modal']/div/div[1]/div[3]/button[1]"; //"//button[contains(text(),'Attach To Message')]";//"//*[@id='upload_file_modal']/div/div[1]/div[3]/button[@data-submit='modal'][1]";//"//*[@id='upload_file_modal']/div/div[1]/div[3]/button[1]";
     public static String btnSendmail = "//*[@id='email_message_buttons']/a[3]";
     public static String filesList = "//div[@class='attachment']";
     public static String btnDeleteResourceOk = ".btn.btn-success";
     public static String txtEmptyResources  = ".alert.alert-warning";
     
     // Contact Manager 2
     
     public static String allActions = "//*[@id='contact-actions']/div/a/span[2]";
     public static String histItems = "//*[@id='history-items']/div/div[2]";
     public static String aletMsg = "div.bootbox-body";
     
    
     public static String eventName = "#pyr_crm_event_title";
     public static String FNameText = "Test"+TestBase.generateRandomNumberInRange(2, 900);
     public static String LNameText = "User"+TestBase.generateRandomNumberInRange(2, 900);
     public static String allContacts = "//div/div/form/div/div[2]";
     public static String toggle =  "button.btn.btn-default.btn-xs.dropdown-toggle";
     public static String grpList = "#listContactGroups > tbody > tr > td:nth-child(2)";
     public static String grpList1 = "#listContactGroups > tbody > tr:nth-child(";
     public static String grpList2=     ") > td:nth-child(2)";
     public static String grpCheckbox2= ") > td:nth-child(1) >input";
     public static String addGrpBtn =  "input#add_to_group.btn.btn-success";
     public static String alrtMsg = "span.help-block";
     public static String alrtMsg2 = "div.alert.alert-danger > ul > li";
     
     public static String lnkAddMoreEmails = "//*[@class='simple_form view-container']/div[3]/div/p/a/span";
     public static String panelAddMoreEmails = "//*[@class='simple_form view-container']/div[3]/div/p";
     
     public static String lnkSendMesg = "//*[@id='contact-actions']/div[2]/a/span[2]";
     public static String lnkSendEcard = "//*[@id='contact-actions']/div[3]/a/span[2]";
     public static String lnkStartCallScript = "//*[@id='contact-actions']/div[4]/a/span[2]";
     public static String lnkNotes = "//*[@id='contact-actions']/div[5]/a/span[2]";
     public static String lnkCreateTask = "//*[@id='contact-actions']/div[6]/a/span[2]";
     public static String lnkCreateEvent = "//*[@id='contact-actions']/div[7]/a/span[2]";
     public static String lnkViewHistory = "//*[@id='contact-actions']/div[9]/a/span[2]";
     public static String lnkAdd2Group = "//*[@id='contact-actions']/div[10]/a/span[2]";
     
     public static String inputSubmitCallscript = "input.btn.btn-default.make_a_call_btn.btn-primary";
     public static String inputAddContactNotes = "textarea#pyr_core_note_content.text.required.form-control";
     public static String btnCreateContactNote = "#new_note_form > div > input.btn.btn-primary";
     
     public static String tblContactNotes = "//*[@id='contact-details-panel']/div[3]";
     public static String inputAddTask = "//input[@value='Add Task']";
     
     public static String tblHistory2 = "//*[@id='contact-details-panel']/div[@id='history-items']";
     public static String tblGroups2 = "//*[@id='list-results']/div[@id='groups-list']";
     
     
     
     //*************************************Tupperware Resource Library*****************************************************//
     
     public static String resActionItems = "html/body/div[2]/div/div/div[2]/div[2]/div[2]/div[1]/section[2]/div/span";
     public static String resListViewAdmin = ".ic-icon-regular.ic-icon-list";
     public static String resTags = "//*[@id='index_page']/div[@class='pull-left']/a";
     public static String lstResTags = "//*[@id='index_page']/div/div[3]/a";
     public static String listResourcesTup = "//*[@id='index_page']/div/table/tbody/tr";
     public static String lstKeywordSearchItemsTup = "//*[@id='keyword_search_report']/div/table/tbody/tr";
 	public static String ResourcesTable = "//*[@id='index_page']/div/div[1]/ul/li/ul";
 	public static String resourcesTableAdmin = "//*[@id='resource-table']/tbody";
     
     //************************************ Widgets ************************************************************************//
     
     public static String newsItems = "//*[@id='company_news']/div[2]/div[1]/div";
     public static String newsList = "//*[contains(@id,'news-')]/div";
     public static String btnAddContact = "//*[@id='new_pyr_crm_contact']/input[2]";
     public static String taskMessage = "Message from "+todaysTask_text;
     public static String businessAlertsList = "//*[@id='business_notifications']/li";
     public static String crmAlertsList = "//*[@id='crm_alerts']/div[2]/ul/li";
     public static String panelCommunityStream = "//*[@id='widget-recent-activity']/div[2]";
     public static String btnAddEvent = "//*[@id='agenda-widget']/div[2]/div[2]/div/a";
     public static String listEvents = "//*[contains(@id,'event')]/td";
     
     
     //************************************** Billing **********************************************************************//
     
     public static String subscriptionTitle = "//*[contains(@id,'edit_pyr_core_subscription')]/div[1]/div[1]/div[2]/h3";
     public static String lstSubPlans = "//*[contains(@id,'edit_pyr_core_subscription')]/div[1]/div/div[2]/div"; 
     public static String lnkBack2Admin = "//*[@id='page']/div[1]/a";
     public static String txtSubPlanLiteMonthly = "Vibe Lite - Monthly";
     public static String txtSubPlanLiteYearly = "Vibe Lite - Yearly";
     public static String txtSubPlanProMonthly = "Vibe Pro - Monthly";//"Free for 2 days + Vibe Pro - Monthly";//
     public static String txtSubPlanProYearly = "Vibe Pro - Yearly";//"Discounted price @ 50.0 for 10 days + Vibe Pro - Yearly";//
     public static String lnkEditCC = "//*[contains(@id,'edit_pyr_core_subscription')]/div[2]/p/a";
     public static String btnSubmitCC = "//*[contains(@id,'edit_pyr_core_credit_card')]/div[11]/input";
     
     public static String inputCCName = "#pyr_core_credit_card_name_on_card";
     public static String inputCCNumber = "#pyr_core_credit_card_card_number";
     public static String inputExpDate = "#pyr_core_credit_card_expire_date";
     public static String inputCVV = "#pyr_core_credit_card_cvv";
     public static String inputAddress1 = "#pyr_core_credit_card_billing_address1";
     public static String inputCity = "#pyr_core_credit_card_billing_city";
     public static String inputPostalCode = "#pyr_core_credit_card_billing_postal_code";
     public static String inputState = "#pyr_core_credit_card_billing_state";
     public static String inputCountry = "#pyr_core_credit_card_billing_country";
     public static String txtValidateCVVByType = "Invalid cvv";
     public static String txtInvalidExpiryDate = "Invalid Expiry Date";
     public static String txtMasterCard = "5555555555554444";
     public static String txtVisaCard = "4111111111111111";
     public static String txtDiscoverCard = "6011111111111117";
     public static String txtAmericanExpressCard = "378282246310005";
     public static String cvvFourDigits = "1234";
     public static String cvvFiveDigits = "12345";
     public static String cvvThreeDigits = "123";
     public static String invalidCVV = "ab1";
     public static String invalidExpiryDate = "12/15";
     public static String inputErrorMsg = "";
     
     public static String lstMarkets = "//*[@id='main-content']/div/div/div[1]/div/div";
     public static String enrollFirstName = "#pyr_core_v2_enrollment_first_name";
     public static String txtEnrollFirstName = "billingf1"; 
     public static String enrollLastName = "#pyr_core_v2_enrollment_last_name";
     public static String txtEnrollLastName = "billingl1"; 
     public static String enrollEmail = "#pyr_core_v2_enrollment_email"; 
     public static String txtEnrollEmail1 = "billinguser1@icentris.com"; 
     public static String txtEnrollEmail11 = "billinguser11@icentris.com";
     public static String txtEnrollEmail2 = "billinguser2@icentris.com"; 
     public static String txtEnrollEmail21 = "billinguser21@icentris.com"; 
     public static String txtEnrollEmail22 = "billinguser22@icentris.com";
     public static String txtEnrollEmail3 = "billinguser3@icentris.com"; 
     public static String txtEnrollEmail31 = "billinguser31@icentris.com";
     public static String txtEnrollEmail4 = "billinguser4@icentris.com"; 
     public static String txtEnrollEmail5 = "billinguser5@icentris.com"; 
     public static String enollBirthday = "#pyr_core_v2_enrollment_birthday";
     public static String txtEnrollBirthday = "01/01/1980";
     public static String enrollPhone = "#pyr_core_v2_enrollment_day_phone";
     public static String txtEnrollPhone = "3458767453";
     public static String enrollGender = "#pyr_core_v2_enrollment_gender"; 
     public static String btnEnrollContinue = "//*[contains(@id,'edit_pyr_core_v2_enrollment')]/div/a";
     
     public static String enrollUserName = "#pyr_core_v2_enrollment_username";
     public static String txtEnrollUserName1 = "BillingUser1"+TestBase.generateRandomNumberInRange(2, 900);
     public static String txtEnrollUserName11 = "BillingUser11"+TestBase.generateRandomNumberInRange(2, 900);
     public static String txtEnrollUserName2 = "BillingUser2"+TestBase.generateRandomNumberInRange(2, 900);
     public static String txtEnrollUserName21 = "BillingUser2"+TestBase.generateRandomNumberInRange(2, 900);
     public static String txtEnrollUserName22 = "BillingUser22"+TestBase.generateRandomNumberInRange(2, 900);
     public static String txtEnrollUserName3 = "BillingUser3"+TestBase.generateRandomNumberInRange(2, 900);
     public static String txtEnrollUserName31 = "BillingUser31"+TestBase.generateRandomNumberInRange(2, 900);
     public static String txtEnrollUserName4 = "BillingUser4"+TestBase.generateRandomNumberInRange(2, 900);
     public static String txtEnrollUserName5 = "BillingUser5"+TestBase.generateRandomNumberInRange(2, 900);
     public static String txtEnrollUserName = "household"+TestBase.generateRandomNumberInRange(2, 900);
     public static String txtEnrollUserName7 = "household"+TestBase.generateRandomNumberInRange(2, 900);
     
     public static String enrollPassword = "#pyr_core_v2_enrollment_password";
     public static String enrollConfirmPassword = "#pyr_core_v2_enrollment_confirm_password";
     public static String txtEnrollPassword = "password1";
     public static String enrollAddress = "#pyr_core_v2_enrollment_tmp_main_address_address1";
     public static String txtEnrollAddress = "Wood Cross 511";
     public static String enrollCity = "#pyr_core_v2_enrollment_tmp_main_address_city";
     public static String txtEnrollCity = "Salt Lake City";
     public static String enrollState = "#pyr_core_v2_enrollment_tmp_main_address_state";
     public static String txtEnrollState = "Utah";
     public static String enrollZipCode = "#pyr_core_v2_enrollment_tmp_main_address_postal_code";
     public static String txtEnrollZip = "12345";
     
     public static String lstSubPlanRows = "//*[contains(@id,'edit_pyr_core_v2_enrollment')]/section/div[2]/section/div/div[@class='row']";
     public static String btnAddCC = "//*[contains(@id,'edit_pyr_core_v2_enrollment')]/section/div[2]/div[4]/div/div/div/div[2]/a";
     public static String ccName = "#pyr_core_v2_enrollment_tmp_credit_card_card_name";
     public static String ccNumber = "#pyr_core_v2_enrollment_tmp_credit_card_card_number";
     public static String ccCvv = "#pyr_core_v2_enrollment_tmp_credit_card_card_cvv";
     public static String ccExpiryYear = "#pyr_core_v2_enrollment_tmp_credit_card_card_expire_year";
     public static String txtCCExpiryDate = "2026";
     public static String ccAddress = "#pyr_core_v2_enrollment_tmp_billing_address_address1";
     public static String ccCity = "#pyr_core_v2_enrollment_tmp_billing_address_city";
     public static String ccState = "#pyr_core_v2_enrollment_tmp_billing_address_state";
     public static String ccZipCode = "#pyr_core_v2_enrollment_tmp_billing_address_postal_code";
     public static String btnCCSave = "//*[contains(@id,'edit_pyr_core_v2_enrollment')]/section/div[4]/div/a";
     
     public static String inputTOC = "#pyr_core_v2_enrollment_toc_acceptance";
     public static String cardName = "//*[contains(@id,'edit_pyr_core_subscription')]/div[2]/div/h3";
     public static String ccCardType = "//*[contains(@id,'edit_pyr_core_subscription')]/div[2]/div/p[1]";
     public static String cardCity = "//*[contains(@id,'edit_pyr_core_subscription')]/div[2]/div/p[3]";
     public static String cardNumber = "//*[contains(@id,'edit_pyr_core_subscription')]/div[2]/div/h5";
     public static String cardAddress = "//*[contains(@id,'edit_pyr_core_subscription')]/div[2]/div/p[2]";
     public static String cardState = "//*[contains(@id,'edit_pyr_core_subscription')]/div[2]/div/p[4]";
     public static String cardZip = "//*[contains(@id,'edit_pyr_core_subscription')]/div[2]/div/p[5]";
     
     public static String btnSumbitSubscription = "//*[contains(@id,'edit_pyr_core_subscription')]/div[3]/input";
     public static String currentSubExpiryDate = "//*[contains(@id,'edit_pyr_core_subscription')]/div[1]/div[1]/div[2]/div[1]";
     public static String nextSubscriptionPlan = "//*[contains(@id,'edit_pyr_core_subscription')]/div[1]/div[1]/div[2]/div[2]";
     
     public static String inputActive = "#pyr_core_subscription_active";
     public static String inputNextBillingDate = "#pyr_core_subscription_next_billing_date";
     public static String inputSubPlan = "#pyr_core_subscription_subscription_plan_id";
     public static String inputSubNotesBillingDate = "//*[@id='subscription_notes']/div[2]/div/div[1]/div";
     public static String inputSubNotesPlan = "//*[@id='subscription_notes']/div[2]/div/div[2]/div";
     
     
   //*****************************Event Types ****************************************//
     public static String eventTitle =  "#main-content > h1";
     public static String eventTypeList = "#event-types > tbody > tr > td:nth-child(3) > span";
     public static String eveField  = "#pyr_core_event_types_-1_name";
     public static String eveColor = "#pyr_core_event_types_-1_color";
     public static String Eventtype1 = "Events Type " + TestBase.generateRandomNumberInRange(10, 2000);
     public static String Eventtype2 = " new "+TestBase.generateRandomNumberInRange(1,10);
     public static String EventName = "PB " + TestBase.generateRandomNumberInRange(10, 2000);
     public static String typeOption =  "#pyr_crm_event_event_type_id";
     
     
     
     //########################################Customer Service Portal##############################################
     
     public static String inputUserEmail = "//*[@id='user_email']";
     public static String inputUserName = "//*[@id='user_username']";
     public static String inputUserPassword = "//*[@id='user_password']";
     public static String inputUserPasswdConfirm = "//*[@id='user_password_confirmation']";
     public static String drpdnUserSecurityGrps = "//*[@id='user_security_group_ids']";
     public static String btnCreateUser = "//*[@id='new_user']/div/div[2]/input"; // The new one is on master"//*[@id='user-create-modal']/div/div[1]/div[3]/button";
     public static String selectSecurityGroups = "//*[@id='user_security_group_ids']";    
     public static String userName_text =  "testUser"+ TestBase.random();
     public static String userName_text2 = "chatUser" + TestBase.random();
     public static String userName_text1 = "aabtestuser" + TestBase.random(); //  asc user name
     public static String userLName_text =  "Lname" + TestBase.random();
     public static String enrollUser = "automation.enroll2";
     public static String userPassword_text = "password1";
     public static String userSecurityGrps_text = "super_admin";
     public static String userSecurityGrps2_text = "billing_admin";
     
     
     
     // Search user
     
     public static String searchUser = "//*[@id='search_text']";
     public static String searchAdminUser = "//input[@type='search']"; 
     public static String tblAdminUsers = "//*[@id='users']/table/tbody";
     public static String lstAdminUsers = "//*[@id='admin_users']/tbody";
         
     // Add Notes to User
     
     public static String inputUserNotes = "//*[@id='pyr_core_note_content']";
     public static String userNotes_text = "new user added " + userName_text;
     public static String btnUserCreateNote = "//*[@id='new_note_form']/div[2]/input";
     
     // Edit User Info
     
     public static String inputUserFName = "//*[@id='user_profile_attributes_first_name']";
     public static String inputUserLName = "//*[@id='user_profile_attributes_last_name']";
     public static String btnUpdateUser = "//*[@id='manage_admin_roles']/div/div[1]/div[2]/form/input[4]";
     public static String manageSecurityGroups = "//*[@class='form-group check_boxes optional user_security_groups']";
     
     // Add Users
     
     public static String inputRoleName = "//*[@id='pyr_core_role_name']";
     public static String inputRoleDesc = "//*[@id='pyr_core_role_description']";
     
     public static String roleName_text = "UserRole_" + TestBase.random();
     public static String roleDesc_text = roleName_text + " description.";
     public static String userSubscription =  "select#pyr_core_subscription_subscription_plan_id";
     public static String userSubscriptionOp =  "select#pyr_core_subscription_subscription_plan_id > option";
     public static String userUpdate =  "input.btn.btn-default.btn.btn-primary";
     
     
     public static String inputFirstName = "#user_profile_attributes_first_name";
     public static String txtUserFirst = "first";
     public static String txtFirstName = "zfirst";
     public static String inputLastName = "#user_profile_attributes_last_name";
     public static String txtLastName = "zlast";
     public static String txtUserLast = "last";
     public static String inputPhone = "#user_profile_attributes_phone";
     public static String inputEmail = "#user_email";
     public static String sortFirstName = "//*[@id='admin_users']/thead/tr/th[1]";
     public static String sortLastName = "//*[@id='admin_users']/thead/tr/th[2]";
     public static String sortUserName = "//*[@id='admin_users']/thead/tr/th[3]";
     public static String sortID = "//*[@id='users']/table/thead/tr/th[4]/a";
     public static String sortEmail = "//*[@id='users']/table/thead/tr/th[5]/a";
     public static String sortByStatus = "//*[@id='admin_users']/thead/tr/th[5]";
     public static String userRow = "//*[@id='users']/table/tbody/tr[1]";
     public static String adminRow = "//*[@id='admin_users']/tbody/tr[1]";
     public static String txtUserFirstName = "//*[@id='users']/table/tbody/tr[1]/td[2]/a";
     public static String txtUserLastName = "//*[@id='users']/table/tbody/tr[1]/td[3]/a";
     public static String txtUserMail = "testuser1@icentris.com";
     public static String txtUserName = "//*[@id='users']/table/tbody/tr/td[4]/a";
     public static String txtUserID = "//*[@id='users']/table/tbody/tr/td[5]";
     public static String txtUserEmail = "//*[@id='users']/table/tbody/tr/td[6]";
     public static String txtUserState = "//*[@id='users']/table/tbody/tr[1]/td[8]";
     public static String txtUserCountry = "//*[@id='users']/table/tbody/tr[1]/td[7]";
     public static String txtUserRank = "//*[@id='users']/table/tbody/tr[1]/td[10]";
     public static String txtUserStatus = "//*[@id='users']/table/tbody/tr[1]/td[12]/span";
     public static String txtUserMarket = "//*[@id='users']/table/tbody/tr[1]/td[9]";
     public static String txtLoggedInMsg = ".caution_msg";
     public static String txtClickHere = ".caution_msg>a";
     public static String txtPageHeading = "#main-content>h1";
     public static String txtpageTitle = ".panel-title";
     
     public static String btnAdvancedSearch = ".btn.btn-info.advanced-search.pull-right";
     public static String userState = "Utah";
     public static String subscription = "Vibe Lite - Monthly";
     public static String inputAdvFirstName = "#pyr_core_advanced_search_form_first_name";
     public static String inputAdvLastName = "#pyr_core_advanced_search_form_last_name";
     public static String inputAdvConsultantID = "#pyr_core_advanced_search_form_consultant_id";
     public static String inputAdvEmail = "#pyr_core_advanced_search_form_email";
     public static String inputAdvUserName = "#pyr_core_advanced_search_form_username";
     public static String inputAdvState = "#pyr_core_advanced_search_form_state";
     public static String inputAdvCountry = "#pyr_core_advanced_search_form_country";
     public static String inputAdvStatus = "#pyr_core_advanced_search_form_active";
     public static String inputAdvRank = "#pyr_core_advanced_search_form_rank";
     public static String inputAdvSubscription = "#pyr_core_advanced_search_form_subscription_plan_id";
     public static String inputAdvMarket = "#pyr_core_advanced_search_form_market_id";
     public static String btnAdvSubmit = "//*[@id='advanced-modal']/div/div[1]/div[3]/button";
     public static String inputenrollType  = "#pyr_core_advanced_search_form_enroll_type";
     public static String footerMessage = ".table>tfoot>tr>td";
     
     
     
     
     
     
     
     
     //enroll a user
     
//     public static String enrollUsername = "billinguser1";
     public static String enrollUserRank = "Diamond";
     public static String enrollUserSubscription = "Vibe Lite - Monthly";
     public static String enrollUserMarket = "US (United States)";
     

/*******************************************Inappropriate Words*******************************************************/
     
     public static String inapTitleText="Inappropriate Words";
     public static String inapsectext2 = "Reserved Words For Site";  
     public static String alertAddNewText="Seachbox cann't be blank!";
     public static String alertSearchText = "You must enter a word to search for.";       
     public static String alertDeleteText = "Please select any badword.";
     public static String alertResDeleteText = "Please select any reserved word.";
     public static String confText = "Word created successfully.";
     public static String confDupeText = "Could not save Words.";     
     public static String deleText = "Word deleted successfully";
     public static String updateText ="Status was successfully updated.";
     public static String searchPlaceHolder = "Enter a word...";
     public static String inapTitle= "#main-content > div > div > div.panel-heading > div.panel-title";
     public static String inapSections= "#tabsContent > paper-tab > div > a";
     public static String inapSearchField= "input#search";
     public static String inapsearchBtn= "input#searchwords.btn.btn-primary";
     public static String inapSearchAllBtn= "input#scan.btn.btn-default";
     public static String inapAddNew = "#badwords > div.search_block > div > input:nth-child(3)";
     public static String inapLang = "select#Language";
     public static String inapFilter= "#badwords > div.col-md-12 > div.inner-title.clearfix > div > input";
     public static String inapWordList= "select#badwords_ddl";
     public static String inapWordListOption= "select#badwords_ddl > option";
     public static String inapEmailLnk= "Email Config";
     public static String inapExportLnk= "Export List";
     public static String inapImportLnk= "Import File";
     public static String inapDeletLnk = "Delete Word";
     
     public static String inapEmaiconfig= "h1#label-for-email_config";
     public static String inapSubjeText= "input#pyr_core_badword_email_subject";
     public static String inapBody= "textarea#pyr_core_badword_email_body";
     public static String inapmailSubmit = "input.btn.btn-default.btn.btn-primary";
     public static String badWordText = "badword"+TestBase.generateRandomNumberInRange(10, 99);
     public static String badWordText2 = "ImproperWords"+TestBase.generateRandomNumberInRange(10, 99);
     public static String reservedWordText = "Reserved"+TestBase.generateRandomNumberInRange(10, 99);
     public static String wordList = "table.table > tbody > tr > td:nth-child(4)";
     public static String wordListBefore = "table.table>tbody>tr:nth-child(";
     public static String wordListAfter = ")>td:nth-child(4)";
     public static String emailAfter = ")>td:nth-child(3) > a";
     public static String UrlAfter = ")>td:nth-child(2) > a";
     public static String emailSubmit = "a.btn.btn-primary";
     public static String photoTitle = "span.photo-title";
     public static String badWordText3 = "ImproperBlog"+TestBase.generateRandomNumberInRange(100, 900);
     public static String blogstatusTextArea= "//*[@id='pyr_community_status_status']";
     public static String updateStatus = "div.actions > input.btn.btn-default";
     public static String resAdd = "form#reserved_words > div.search_block > div.buttons > input.btn.btn-default";
     public static String resSearchField = "form#reserved_words > div.search_block > input#search";
     public static String resDel = "form#reserved_words > div.search_block > div.buttons > a.btn.btn-default";
     public static String reseList = "select#reserved_list_ddl > option";
     public static String emailSubText = "You have Posted bad words " + TestBase.generateRandomNumberInRange(100, 900);
     public static String emailBodyText = "bad words " + TestBase.generateRandomNumberInRange(100, 900);
     public static String emailConfigSubmit = "input.btn.btn-default.btn.btn-primary";
     
     
     
     //************************************** FeedBack **********************************************************************//
       public static String feedbackText = "Feedback And Support";
       public static String fbTitleText = "Feedback";
       public static String fbViewText = "View Tickets";
       public static String fbReportText = "Report An Issue";
       public static String fbFeedbackText = "Feedback";
       public static String fbSubmitText = "Submit";
       public static String fbTitcketTitle =  "The worst feeling is feeling "+ TestBase.generateRandomString();
       public static String fbUsersTitcketTitle =  "Users Report "+ TestBase.generateRandomString();
       public static String fbcomment = "Useless comment "+TestBase.generateRandomNumberInRange(1, 5000);
       public static String fbFeedbackTitle =  "Awesome photo "+ TestBase.generateRandomString();
       public static String fbFeedbackcomment = "Useful information "+TestBase.generateRandomNumberInRange(1000, 5000);
       public static String fbUsersTitcketComment =  "user Commented as "+ TestBase.generateRandomString();
       public static String adminComment =  "Admin commented as  "+ TestBase.generateRandomString();
       public static String navFeedBack = "#page > a.feedback-float";
       public static String fbTitle = "#label-for-feedback_modal";
       public static String fbViewTickets = "#view_tickets";
       public static String fbViewFeedback = "#view_feedbacks";
       public static String fbReport =   "#contact-form-div > a.btn.btn-primary";
       public static String fbFeedback = "//*[@id='feedback-form-div']/a";
       public static String fbMsgTitle = "form#new_pyr_community_feedback > div.form-group.string.required.pyr_community_feedback_title > input#pyr_community_feedback_title";//"input#pyr_community_feedback_title";
       public static String fbDescp = "//*[@id='pyr_community_feedback_description']";
       public static String fbChooseFile = "#pyr_community_feedback_file";
       public static String fbEmailTextbox = "#pyr_community_feedback_email";
       public static String fbSubmit = "form#new_pyr_community_feedback > div.actions > input#ticket.btn.btn-default.btn.btn-primary";
       public static String fbVote = "#left > div:nth-child(2) > p > small > span:nth-child(1) > i";
       public static String fbComment= "#left > div:nth-child(2) > p > small > span:nth-child(2) > i";
       public static String fbTickList ="#view_tickets > table > tbody > tr > td:nth-child(2)";            //"div#view_tickets > div > div.ticket-info > div.ticket_title > span";
       public static String fbTickList1 = "#view_tickets > table > tbody > tr:nth-child(";         //"div#view_tickets > div:nth-child(";
       public static String fbTickList2 = ") > td:nth-child(2)";   // ") > div.ticket-info > div.ticket_title > span";
       public static String fbTicComment = ") > td > div.row.votes > div > span > a";//") > div.row.votes > div > span > a";
       public static String fbCommentTextField = "div.form-group.text.required.pyr_community_comment_content > textarea#pyr_community_comment_content";
       public static String fbCommSubmit = "div > div > span > input.btn.btn-primary";
       public static String fbExistComment = "div > div.media-body > p.activity-content";
       public static String fbExistCommentB4r = "div:nth-child(";
       public static String fbExistCommentA4r = ") > div.media-body > p.activity-content";
       public static String fbDelete = ") > div.media-body > span > a.ic-icon.ic-icon-close";
       public static String fbReportLnk = ") > div.media-body > span > a.report_link";
       public static String fdAdminList = "#feedback_all > div > div > div.col-md-11 > a";
       public static String fdAdminListB4r = "#feedback_all > div:nth-child(";
       public static String fdAdminListAtr = ") > div > div.col-md-11 > a";
       public static String fdAdminListChkBxAtr = ") > div > div > input#feedbacks_";
       public static String fbAdminDelete ="a#all_tickets.btn.btn-danger.btn-sm";  //"div.row.feedback-admin > div.col-md-4.feedbacks-tickets > div#feedback_actions >a#all_tickets.btn.btn-danger.btn-sm";
       public static String fbFeedbackSubmit = "form#new_pyr_community_feedback > div.actions > input#idea.btn.btn-default.btn.btn-primary";
       public static String fbFeedList ="#view_feedbacks > table > tbody > tr > td:nth-child(1)";  //     "div#view_feedbacks > div > div.ticket-info > div.feedback_title > span";
       public static String fbFeedList1 ="#view_feedbacks > table > tbody > tr:nth-child(";  //"div#view_feedbacks > div:nth-child(";
       public static String fbFeedList2 =") > td:nth-child(1)";    //") > div.ticket-info > div.feedback_title > span";
       public static String fbFeedComment = ") > td > div.row.votes > div > span:nth-child(4) > a";
       public static String fbCommnet ="textarea#pyr_community_comment_content";    //"div.comments > div > form > div.row-custom > div.input-group >  div.form-group.text.required.pyr_community_comment_content > div.label-with-hint > span ";           //> textarea#pyr_community_comment_content";
       public static String fbCommPost = "span.input-group-btn > input.btn.btn-primary";
       public static String fbFeedCommentCount = ") > td > div.row.votes > div > span:nth-child(2)";
       public static String fbFeedVoteCount = ") > td > div.row.votes > div > span:nth-child(1)";
       public static String fbFeedVote = ") > td > div.row.votes > div > span:nth-child(3) > a";
       public static String fbTickSearh= "input#ticket_query";
       public static String fbFeedbackSearh= "input#feedback_query";
       public static String fbFeedbackInvalidSearh= "div#view_feedbacks > div.row";
       public static String fbTicketsInvalidSearh= "div#view_tickets > div.row";
       public static String InvalidTicketText= "There are no tickets found.";
       public static String InvalidFeedbackText= "There are no feedbacks found.";
       public static String fbFilter = "button#filterid";
       public static String filterOpt = "ul.dropdown-menu.filter-by > li > a";
       public static String fbReportTextarea = "textarea#pyr_community_abuse_report_abuse_reason";
       public static String fbRepbtn =  "div.col-md-12.no-gutter > input.btn.btn-primary";
       public static String fbAdminRep = "div.media-body > span.text-danger";
       
       public static String fbAdmFeedAll = "div#feedbacks-all-div.feeds > a.links";
       public static String fbAdmFeedOpen = "div#feedbacks-open-div.feeds > a.links";
       public static String fbAdmFeedArchived = "div#feedbacks-archived-div.feeds > a.links";
       public static String fbAdmTickClosed = "div#tickets-closed-div.feeds > a.links";
       public static String fbAdmTickOpen = "div#tickets-open-div.feeds > a.links";
       public static String fbAdmTickAll= "div#tickets-all-div.feeds > a.links"; 
       public static String feedDeleteInAdmin = "a#all_feedbacks.btn.btn-danger.btn-sm";
    /*   
       public static String fbTickListBfr  = "";
       public static String fbTickListAft = "";*/
       
       
       
       
 
     public static String btnResetPwd = "//*[@id='main-content']/div[1]/div[2]/div[1]/div[2]/a[3]";
     public static String btnConfirmResetPwdOk = "html/body/div[6]/div/div/div[2]/button[2]";
     public static String subjectResetEmail = "Password Reset";
     public static String inputResetPwd = "#user_password";
     public static String inputResetConfirmPwd = "#user_password_confirmation";
     public static String btnUpdatePwd = "//*[@value='Update Password']";
     public static String validateResetPwd = "#span_password";
     public static String validateConfirmResetPwd = "#span_password_confirmation";
     public static String passwordsValidate = ".help-block";
     public static String validatePwdMatch = "#password_confirm";
     public static String drpdownPwdRegex = "//*[@id='password_regex']";
     public static String btnUpdatePwdSettings = "//input[@value='Update Values']";
     public static String lstWebSites = "//div[@class='controls']/table/tbody/tr";
     public static String tabLogin = "//*[@id='tabsContent']/paper-tab[2]/div/a";
     public static String tabSubscriptions = "//*[@id='tabsContent']/paper-tab[3]/div/a";
     public static String tabUserStats = "//*[@id='tabsContent']/paper-tab[5]/div/a";
     public static String btnUpdateUserDetails = "//input[@label='Submit']";
     public static String txtEmail = ".table.info_table>tbody>tr>td>a";
     public static String inputPwpSite = "#pyr_pwp_site_name";
     public static String btnUpdatePwp = "//*[@type='submit']";
     public static String drpSubPlan = "#user_subscription_attributes_subscription_plan_id";
     public static String lstSecGroups = "#user_security_group_ids>option";  
     public static String lblCommFirstName = "//*[contains(@id,'edit_pyr_core_profile')]/section[1]/div/div/table/tbody/tr[1]/td[1]";
     public static String lblCommLastName = "//*[contains(@id,'edit_pyr_core_profile')]/section[1]/div/div/table/tbody/tr[2]/td[1]";
     public static String inputAccntFirstName = "#pyr_tree_user_first_name";
     public static String inputAccntLastName = "#pyr_tree_user_last_name";
     public static String inputAccntPhone = "#pyr_tree_user_phone";
     public static String inputAccntEmail = "#pyr_tree_user_email";
     public static String inputAccntAddress1 = "#pyr_tree_user_address1";
     public static String inputAccntCity = "#pyr_tree_user_city";
     public static String btnUpdateAccntInfo = "#submit";
     public static String lblLoginHistory = "//*[@id='main-content']/div[1]/div[2]/div[2]/div[1]/iron-pages/div[5]/div[1]/div[1]/div";
     public static String lblStatus = "//*[@id='main-content']/div[1]/div[2]/div[2]/div[1]/iron-pages/div[5]/div[2]/div[1]/div";
     
     // shops2
     
     public static String oneTime = "input#cart_form_cart_one_time";
     public static String shipNext = "span > input.btn.btn-primary";
     public static String shipSave = "input.btn.btn-primary";
     public static String emptyAlert = "p.alert.alert-info";
     public static String emptyAlertText = "Your cart is empty";
     public static String userloginBtn =  "div.form-group > input.btn.btn-primary";
     public static String emailWishBtn = "button#email_a_friend_btn";
     public static String shopSubject =  "Awsome products "+TestBase.generateRandomNumberInRange(1, 200);
     public static String wishList  = "#line_items > tr > td.cart-item-description > h4 > a";
     public static String wishListBfr  = "#line_items > tr:nth-child(";
     public static String wishListAft  = ") > td.cart-item-description > h4 > a";
     public static String add2CartAft  = ") > td:nth-child(5) > form > a.btn.btn-primary.btn-sm.wishlist-add-to-cart";
     public static String quanPlustAft = ") > td.cart-item-quantity >div > div.number-spinner-more";
     public static String quantityText = "input#order_line_items_attributes_0_quantity";
     public static String todaysOrder  = "#line_item ";
     public static String orderClose = "//*[@id='order-thank-you']/div/div[1]/div[1]/button";
     public static String orderId = "//*[@id='order-thank-you']/div/div[1]/div[2]/h1";
     public static String orderIdText = "div.modal-content > div.modal-body > strong";
     public static String orderHist = "#order_summary>legend";
     public static String orderList = "table > tbody > tr";
     public static String ordIdBfr = "table > tbody:nth-child(2) > tr:nth-child(";
     public static String ordIdAft = ") > td:nth-child(2)";
     public static String ordDetailsBtn =") > td:nth-child(6) > a";   //") > tr:nth-child(1) > td:nth-child(6)";
     public static String orderedItems = "#line-items > tbody > tr > td > h4";
     public static String orderedItemsBfr = "#line-items > tbody > tr:nth-child(";
     public static String orderedItemsAfr = ")> td > h4 > a";
     public static String shopTitle = "div.panel-heading > div.panel-title > a";
     public static String createAutoBtn = "div.input-group-btn > a.btn.btn-default";
     public static String btnAddToAutoship = "div.pull-left.add-autoship-btn > input[type='submit']:nth-child(4)";
     // Autoship
     public static String nextInProdAutoBtn =  "div > a.btn.btn-primary";
     public static String autoId = "div.panel-heading.ohistory > div > div.col-md-3 > div:nth-child(1) > a";
     public static String autoList = "div > div > div > div > div.col-md-3 > div:nth-child(1) > a";
     public static String autoListBfr ="div:nth-child(";
     public static String autoListAft = ") > div > div >div > div.col-md-3 > div:nth-child(1) > a";
     public static String autoDeleAft = ") > div > div >div > div.col-md-3 > div:nth-child(2) > a.btn.btn-default";
     public static String autoEditAft = ") > div > div >div > div.col-md-3 > div:nth-child(2) > a.btn.btn-primary";
     public static String autoship_id = "#autoship_id";
     public static String userIconInShop =  "div.dropdown > a.dropdown-toggle.profile_name";
     public static String logoutCust =  "ul > li:nth-child(2) > a";
     public static String custCheckOut = "#login_tab > div.row > div.col-md-3 > label";
     public static String guestCheckOut = "#login_tab > div.row > div.col-md-9 > label";
     public static String shopEmail = "input#order_email";
     public static String emailCont = "div.form-group > input.btn.btn-primary.btn-block";    
     public static String guestFname = "guest."+TestBase.generateRandomNumberInRange(1, 500);
     public static String guestLname = "shopper";
     public static String guestEmail = guestFname+"@gmail.com"; 
     public static String county = "input#order_ship_address_attributes_county";
     public static String gogHome = "div.CjySve";
     public static String gogList = "div.aMeBDb.GU7x0c";
     public static String exisCustLogin = "#customer-tabs > li:nth-child(2) >a";
     public static String newCustLogin = "#customer-tabs > li:nth-child(1) >a";
     public static String guestLogin = "#customer-tabs > li:nth-child(3) >a";
     
     

     public static String  txtProdName = "PROD001";
     public static String  spreeAssetTitle = "Test Asset " + TestBase.generateRandomNumberInRange(1, 1000);
     public static String  txtVideoAsset = "Product Video URL " + TestBase.generateRandomNumberInRange(1, 1000);
     public static String  txtVirtualProdName1 = "TestVProd"  + TestBase.generateRandomNumberInRange(1, 1000);
     public static String  txtVirtualProdName2 = "Prod Books "  + TestBase.generateRandomNumberInRange(1, 1000);
     public static String  txtVirtualProdName = "Hydrate 30 Pack ";         // "VPROD80"; // "VPROD" + TestBase.generateRandomNumberInRange(1, 1000);  // "TATAPROD2016";   // final,  "VPROD002";
     public static String  txtProdSKU = "SKU" + TestBase.generateRandomNumberInRange(1, 1000);
     
     public static String  inputSearchProdName = "//*[@id='q_name_cont']";
     public static String  inputSearchSKUName = "//*[@id='q_variants_including_master_sku_cont']";
     public static String  inputSearchMarket = "//*[@id='q_market']";
     public static String  btnSearchProduct = "//*[@id='spree/product_search']/div[2]/button";
     
     public static String panelProductsList = "//table[@id='listing_products']";
     
     // Option Types page
     
     public static String  txtOptiontypeName = "TeaFlavour"+TestBase.generateRandomString();
     public static String  lnkAdminNewOptionTypes = "//*[@id='new_option_type_link']";
     public static String  inputOptionTypeName = "//*[@id='option_type_name']";
     public static String  inputOptionTypePresentaion = "//*[@id='option_type_presentation']";
     public static String  btnCreateOptionType = ".btn.btn-primary.btn-success";
     
     public static String  tblOptionTypes = "//table[@id='listing_option_types']/tbody";
     public static String  alertSuccess = "//div[@class='alert alert-success']";
     public static String  alertSuccessMsg = "Option Type "+txtOptiontypeName+" has been successfully removed!";
     
     // Shopping Admin - Product Details
     
     public static String lnkShopDetails = "//*[@id='sidebar']/ul/li[1]/a/span[2]";
     public static String inputCostPrice = "//*[@id='product_cost_price']";
     public static String inputProdCostCurrency = "//*[@id='product_cost_currency']";
     public static String inputProdShortDesc = "//*[@id='product_short_description']";
     public static String inputProdTaxons = "//*[@id='s2id_product_taxon_ids']/ul/li[1]/input";
     public static String inputProdOptionTypes = "//*[@id='s2id_product_option_type_ids']/ul/li/input";
     public static String txtSkuValue = "08-0012"; //"15-008";
    
     // Shopping Admin - Image 
     
     public static String lnkShopImage = "//*[@id='sidebar']/ul/li[2]/a/span[2]";
     public static String lnkAddImage = "//*[@id='new_image_link']/span[2]";
     public static String browseAttachment = "#image_attachment";
     public static String inputAltText = "//*[@id='image_alt']";
     public static String btnCreateShopImg = "fieldset > div.form-actions > button.btn.btn-primary";
     
     // Shopping Admin - Price
     
     public static String lnkShopPrices = "//*[@id='sidebar']/ul/li[7]/a/span[2]";
     
     public static String inputRetailPrice = "//*[@id='variant-prices']/div/div/div[1]/div[2]/div[2]/input";
     public static String inputRetailPV = "//*[@id='variant-prices']/div/div/div[1]/div[2]/div[4]/input";
     public static String inputRetailCV = "//*[@id='variant-prices']/div/div/div[1]/div[2]/div[6]/input";
     
     public static String inputPreferedCustomerPrice = "//*[@id='variant-prices']/div/div/div[2]/div[2]/div[2]/input";
     public static String inputPreferedCustomerPV = "//*[@id='variant-prices']/div/div/div[2]/div[2]/div[4]/input";
     public static String inputPreferedCustomerCV = "//*[@id='variant-prices']/div/div/div[2]/div[2]/div[6]/input";
     
     public static String inputWholesalePrice = "//*[@id='variant-prices']/div/div/div[3]/div[2]/div[2]/input";
     public static String inputWholesalePV = "//*[@id='variant-prices']/div/div/div[3]/div[2]/div[4]/input";
     public static String inputWholesaleCV = "//*[@id='variant-prices']/div/div/div[3]/div[2]/div[6]/input";
     
     public static String btnUpdatePrices = "div.form-actions > button.btn.btn-primary";
     
     // Shopping Admin - Assets
     
     public static String lnkShopAssets = "//*[@id='sidebar']/ul/li[9]/a/span[2]";
     public static String inputProdAssetTitle = "//*[@id='product_asset_title']";
     public static String browseDocument = "#product_asset_file_path"; //"//*[@id='product_asset_file_path']";
     public static String btnCreateProductAsset = "//*[@id='upload_asset']";
     
     public static String selectVideoType = "//*[@id='video_type']";
     public static String inputShoppingVideoTitle = "//*[@id='video_title']";
     public static String inputShoppingVideoURL = "//*[@id='video_url']";
     public static String btnCreateVideoAsset  = "//*[@id='upload_video_asset']";
     
  // Shopping Admin - Variants
     
     public static String  drpdnVariantOption = "//*[@id='s2id_variant_option_value_ids']";
     public static String  inputSKUValue = "//*[@id='variant_sku']";
                                             
     
   // Admin -> Shop -> Settings page
     
     public static String titleGeneralSettings = "//*[@id='page-header']/div/div/h1";
     public static String inputPreferenceLiveMarkets = "#preferences_live_markets";
     public static String inputPreferenceNFRMarkets = "#preferences_nfr_markets";
     public static String drpdnPreferenceBillingAddressSettings = "#preferences_show_all_live_markets_for_billing_address";
     public static String drpdnPreferenceReviewsSettings = "#preferences_reviews_enabled";
     public static String drpdnPreferenceWishlistSettings = "#preferences_wishlist_enabled";
     public static String drpdnPreferenceAnalyticsSettings = "#preferences_product_analytics_enabled";
     public static String drpdnPreferenceShowRecentlyReviewsItems = "#preferences_recents_recently_viewed_products_enabled";
     public static String drpdnPreferenceShowRecentlyReviewsItemsCnt = "#preferences_recents_recently_viewed_products_max_count";
     public static String drpdnPreferencesShowMostPopularCategories = "#preferences_category_most_popular_enabled";
     public static String drpdnPreferencesAllowOrdersInOtherMarkets = "#preferences_allow_orders_in_other_markets";
     public static String drpdnPreferencesShowReorderInOrderHistory = "#preferences_show_reorder_in_order_history";
     public static String drpdnPreferencesCanDeleteFinalAutoship =  "#preferences_can_delete_final_autoship";
     public static String drpdnPreferencesCanDisplayAutoshipOnHomepage = "#preferences_can_dispaly_autoship_on_home_page";
     public static String drpdnPreferencesCanDisplayAutoshipOnCartpage = "#preferences_can_dispaly_autoship_on_cart_page";
     public static String radioPreferenceSingleAUtoship = "#preferences_autoship_single";
     public static String radioPreferenceMultipleAUtoship = "#preferences_autoship_multiple";
     
     
     public static String txtPreferenceLiveMarkets = "US,CA,RU";
     public static String txtPreferenceNFRMarkets = "Ru";
     public static String txtPreferenceBillingAddressSettings = "No";
     public static String txtPreferenceReviewsSettings = "No";
     public static String txtPreferenceWishlistSettings = "No";
     public static String txtPreferenceAnalyticsSettings = "No";
     public static String txtPreferenceShowRecentlyReviewsItems = "No";
     public static String txtPreferencesShowMostPopularCategories = "No";
     public static String txtPreferencesAllowOrdersInOtherMarkets = "No";
     public static String txtPreferencesShowReorderInOrderHistory = "No";
     public static String txtPreferencesCanDeleteFinalAutoship = "No";
     public static String txtPreferencesCanDisplayAutoshipOnHomepage = "No";
     public static String txtPreferencesCanDisplayAutoshipOnCartpage = "No";     
     public static String btnUpdateFetureSettings = ".form-buttons.filter-actions.actions > .btn.btn-primary";
     
     // Product Reviews Container (user side)
     
     public static String panelReviews = "//div[@id='reviews-container']";
     
     // Shop logout
     
     public static String shopProfileDrpdn = "div.dropdown > a.dropdown-toggle.profile_name";
     public static String shopLogout = "ul.dropdown-menu>li:nth-child(2) >a";
     
     
     // Product Analytics Settings at User
     
     public static String panelProdAnalytics = "//*[@class='prod_analytics clearfix']";
     public static String panelRecentlyViewed = "//*[@class='recent-view-products clearfix']";
     
     public static String lnkReorder = "//a[contains(text(),'Reorder')]";
     
   
     // ****************ECARDS******************************************************//
     public static String tagText = "tags with id "+TestBase.generateRandomNumberInRange(100, 1000);
     public static String ecardCatSubText = "Welcome "+TestBase.generateRandomNumberInRange(100, 1000);
     public static String ecardCatSubText2 = "Hearty Welcomes "+TestBase.generateRandomNumberInRange(100, 1000);
     public static String ecardCatName = "Health "+TestBase.generateRandomNumberInRange(1, 10);
     public static String ecardtempName = "template"+TestBase.generateRandomNumberInRange(10, 50);
     public static String saveAstempName = "Indian Festival "+TestBase.generateRandomNumberInRange(10, 500);
     public static String eCardParentCat ="div > ul.panel-nav.categories > li";  //:nth-child(2)"; > a:nth-child(2)
     public static String eCardParentCatBfr ="div > ul.panel-nav.categories > li:nth-child(";
     public static String eCardParentCatAft =")";
     public static String eCardParentCatPlusIcon ="div > ul.panel-nav.categories > li > a:nth-child(1)";
     public static String eCardrows ="div.ecard-templates-grid > div.row";
     public static String eCardrowsBfr ="div.ecard-templates-grid > div:nth-child(";
     public static String eCardrowsAft =")";
     public static String eCardTemp ="> div > div > div > div > div.resource-title";     
     
     public static String eCardTempBfr ="> div > div:nth-child(";
     public static String savedECardTempBfr ="div > div:nth-child(";
     public static String eCardTempAfr =") > div > div > div.resource-title";
     public static String eCardToggleDropDown =") > div > div > div.more-options > div > button.btn.dropdown-toggle.btn.btn-default.btn-xs > i";
     public static String ecardTitle = "div.modal-title";
     public static String ecardTitleText = "Let's Create Ecard";
     public static String ecardSub = "input#pyr_crm_ecard_subject";
     public static String ecardSaveAs_old =  "div.row > div.col-md-2.col-xs-2.text-right.save-mobile > a" ;                           //"div.text-right > a.btn.btn-default";
     public static String ecardSaveAs ="div.col-md-2.text-right > a.btn.btn-primary"; //"div.text-right > a.btn.btn-default";
     public static String ecardNext= "div.text-right > input.btn.btn-primary";
     public static String temptitleText = "Template Title";
     public static String temptitle = "div.modal-body > label";
     public static String tempLab = "div.modal-header.clearfix > h1#label-for-save_modal";
     public static String tempLabText = "Save Template";     
     public static String temptitleInput = "input#template_title";
     public static String saveTemp = "div.modal-footer > button.btn.btn-default";
     public static String savedTitles = "div > div.resource-title";
     public static String addMail = "div.text-right > a.btn";
     public static String addGrpAdd = "div.row > div.col-md-12.text-right > button.btn.btn-primary";
     public static String mailSelectAll = "input#select_all";
     public static String ecardMailSend= "div.text-right > button.btn.btn-primary";
     public static String ecardConfrim= "div.col-md-12.text-center.message.noBorder > h3";
     public static String ecardConfrimText = "Congratulations, You've successfully Sent the Ecard";
     public static String inputManualEmail =  "input#email.email.form-control";
     public static String ecardListView = "a.list-view > i.ic-icon-regular.ic-icon-list";
     public static String ecardGridView = "a.grid-view > i.ic-icon-regular.ic-icon-grid";     
     public static String recipAlert = "div.modal-body > div.bootbox-body";
     public static String recipAlertText = "Please add * recipient to your message.";
     public static String recpOkBtn = "div.bootbox.modal.fade.bootbox-alert.in > div.modal-dialog > div > div.modal-footer > button.btn.btn-primary";
     public static String ecardFirstTitle = "div > div.resource-title";
     public static String ecardListTitle = "tr > td > a";
     public static String savedCard = "Saved Ecards";
     public static String listcards = "table.table.list_view>tbody > tr";
     public static String cardNameBfr = "table.table.list_view>tbody>tr:nth-child(";
     public static String cardNameAfr = ") > td:nth-child(2) > a";
     public static String cardOptionsAfr = ") > td:nth-child(4) > div.more-options > div > button";
     public static String cardOptionEdit =   ") > td:nth-child(4) > div.more-options > div >ul > li:nth-child(1) > a";
     public static String cardOptionDelete = ") > td:nth-child(4) > div.more-options > div >ul > li:nth-child(2) > a";
     public static String deleteOkBtn = "button.btn.btn-success";
     public static String mailBfr = "div:nth-child(";
     public static String mailAfr = ") > div.row > div > input#email";
     public static String moremailsLink = "div.modal-body > div.row > div.col-md-12 > a";
     public static String readbleSubject = "#email_subject";
     public static String mailText = "div.mf_container > ol > li";
     public static String headerMail = "h1#label-for-ecard_manual_contacts";
     public static String headerMailText = "Add Manually";
     public static String libTitle= "div.panel-title";
     public static String libTitleText ="Ecard Library"; 
     public static String ecardSearch = "input#ecard_query";
     public static String ecrdSearchPlaceHolder = "Search Ecard Library";
     public static String hintMsg = "Subject can't be more than 250 characters";
     public static String subHint = "div.form-group.string.optional.pyr_crm_ecard_subject > div > span.hint-block > p.help-block" ;//"form > div.form-group.string.optional.pyr_crm_ecard_subject > div > span.hint-block";
     public static String subHintMsg = " > p.help-block";
     public static String socialNetwork = "a.btn.btn-info.btn-sm.share-social-get-url.get-share";
     public static String fbNavgiation = "a#pageLoginAnchor > div#userNavigationLabel";
     public static String fbLogOut = "form#show_me_how_logout_1";
     public static String mailGrp = "#ecard_contact_groups_container > div > div.media-body";
     public static String mailGrpBfr= "#ecard_contact_groups_container > div:nth-child(";
     public static String mailGrpAft= ") > div.media-body";
     public static String mailGrpChkBox= ") > div > input";
     public static String ecardComm = "textarea#pyr_crm_ecard_comment_share_comment";
     public static String commEcardShare = "ecard shared "+ TestBase.generateRandomString();
     public static String commShareBtn = "form#new_pyr_crm_ecard_comment_share > div > input.btn.btn-primary";
     public static String dragWidgetToLocationInHome = "//*[@id='widget_manager_home']/div[2]/div/div"; 
     public static String saveAsDraft = "span.action-buttons > input.btn.btn-default.btn.btn-default";
     public static String bodyCompose  =  "textarea#pyr_crm_ecard_message"; 
     public static String footerCompose  =  "textarea#pyr_crm_ecard_footer"; 
     public static String mailIcon = "input.mail-btn";
     public static String eFB = "div.col-md-10.social-media > div > a.addthis_button_facebook";
     public static String eTwitter = "div.col-md-10.social-media > div > a.addthis_button_twitter";
     
     
     
     
     //MyProfile - 09/21/2016
     public static String blgList = "article > div.post-heading > h3 > a";
     public static String myPostTitle = "Blog Id "+ TestBase.generateRandomNumberInRange(1,99);
     public static String postTitleUpdated = myPostTitle+" updated";
     public static String myProfPhotoTitle= "myPP " + TestBase.generateRandomNumberInRange(1, 256);
     public static String titleUpdated = "myPPNew" + TestBase.generateRandomNumberInRange(1, 256);
     public static String videoTitle = "myPV" + TestBase.random();
     public static String shareBtn = "div.actions > input.btn.btn-default";
     public static String titVid ="#profile_videos > div.panel-body > div > div > span" ;
     public static String titVidBfr = "#profile_videos > div.panel-body > div > div:nth-child("; 
     public static String titVidAft = ") > span"; 
     public static String titVidImgAft = ") > div > a > img";
     public static String vidAllImgs ="#profile_videos > div.panel-body > div > div  > div > a > img" ;
     
     //Notifications - 09/22/2016 - ravi
     public static String markAllSeen = "#main-content > div > div.panel-heading > div.panel-tools ";
     public static String msgLink  = "div:nth-child(2) > div > div > div > a > div > div.media-heading";     

     // ENROLLMENT
     
     public static String[] enrollEnableMarketlist = {"US"};
     public static String[] enrollDefaultMarketlist = {"US"};
    
     public static String[] enrollPersonalDetailsFields = {"Email", "Gender", "Day phone", "First name", "Last name"};
     public static String[] enrollPersonalDetailsRequiredFields = {"Email", "First name"};
     public static String[] enrollPersonalDetailsConfirmFields = {};
    	 
    /* public static String[] enrollLoginDetailsFields = {"Billing","Subscription with promotion", "Username", "Password"};
     public static String[] enrollLoginDetailsRequiredFields = {"Billing","Subscription with promotion", "Username", "Password"};
     public static String[] enrollLoginDetailsConfirmFields = {"Password"};*/
     
     public static String[] enrollLoginDetailsFields = {"Username", "Password"};
     public static String[] enrollLoginDetailsRequiredFields = {};
     public static String[] enrollLoginDetailsConfirmFields = {};
     
     public static String[] enrollFinalReviewFields = {"Toc acceptance"};
     public static String[] enrollFinalReviewRequiredFields = {"Toc acceptance"};
     public static String[] enrollFinalReviewConfirmFields = {};
     

     
    public static String[] enrollProfileConfigFields={"Can login","Customer type","Insert enroller level tree", "Price type", "Sponsor", "Work phone"}; // LPGN
  
     public static String[] enrollPaymentOptions = {"Credit"};
     
     public static String enrollCanLogin_text = "true";
     public static String enrollCustomerType_text = "3";
     public static String enrollInsertEnrollerLevelTree_text = "true";
     public static String enrollPriceType_text = "4";
     public static String enrollSponsor_text = "315351";
     public static String enrollSubscriptionWithPromotion_text = "true";	 
     
     public static String enrollPDBirthday_text = "28/06/1982";
     public static String enrollPDDayPhone_text = "4354322";
     public static String enrollPDEmail_text = "vibe.icentris@gmail.com";
     public static String enrollPDFirstName_text = "jayadev";
     public static String enrollPDGender_text = "Male";
     public static String enrollPDLastName_text = "mootha";
     
     public static String enrollLDCardName_text = "Master Card";
     public static String enrollLDCardNumber_text = "5123456789012346";
     public static String enrollLDCardCvv_text = "100";
     public static String enrollLDCardExpiryMonth_text = "May";
     public static String enrollLDCardExpiryYear_text = "2017";
     
     public static String enrollLDAddress1_text = "WoodLands";
     public static String enrollLDCity_text = "Cupertino";
     public static String enrollLDState_text =  "Alberta";  // "California";
     public static String enrollLDPostalCode_text = "412121";
     public static String enrollLDCountry_text = "Canada";   // "United States";
     
     public static String enrollLDPasswd_text = "password1";
     public static String enrollLDConfirmPasswd_text = "password1";
     
     public static String enrollDPUserName_text = "CAUser" + TestBase.generateRandomNumberInRange(1, 1000);
     
     public static String expEnrollMsg = "Your enrollment is successful. Your consultant id is";
     
     //*************************************** Settings *************************************************//
     
     public static String inputPeopleSearch = "//*[@id='pyr_community_people_search_form_search_text']";
     public static String btnEditPhone = "//*[contains(@id,'edit_pyr_core_profile')]/section[1]/div/div/table/tbody/tr[4]/td[2]/a";
     public static String txtDisplayPhone = "//*[@id='pyr_core_profile_phone_text']";
     public static String inputCommPhone = "//*[@id='pyr_core_profile_phone']";
     public static String btnCommunityPhoneSave = "//*[@id='phoneFieldModal']/div/div[1]/div[3]/button";
     public static String txtDisplayFirstName = "//*[@id='pyr_core_profile_first_name_text']";
     public static String txtDisplayLastName = "//*[@id='pyr_core_profile_last_name_text']";
     
     
     //community -09/22/2016 - Ravi
     public static String photoTitles = "//div[@class='activities-stream']/div/div/div[1]/a";
     public static String photoTitlesBfr = "//div[@class='activities-stream']/div[";
     public static String photoTitlesAft = "]/div/div[1]/a";
     public static String reportRHideAft = "]/div/div[2]/span[1]/span[5]/a";
     public static String reportRHideAft2 = "]/div/div[2]/span[1]/span[4]/a";
     
     
     // Leadership Reports 
     
     public static String  inputUplineAccountNo = "//*[@id='leadershipPerformance$$TL_USER_ID$$entry']";
     public static String leadChildWinClose = "#buttonContainer > button:nth-child(2) > div > div > span";
     public static String leadHeadTabs = "li.topLevel > a";
     
     
     // User Tasks Pane
     
     public static String tasksContainer = "//*[@id='widget_manager_tasks']/div[2]/div/div";
     public static String tasksLeftPane = "//*[@id='widget_manager_tasks']/div[2]/div/div/div[1]";
     public static String tasksRightPane = "//*[@id='widget_manager_tasks']/div[2]/div/div/div[2]";
     public static String deleteWidgets = "div.widget-controls > span.icon > i.ic-icon-regular.ic-icon-trash";
     
     
//Reports - 02/10/2016
     
   //  public static String repId = "02857271";
     public static String resultsCount = "#page_entries_info > span.badge";
     public static String tabColmns = "table > thead > tr > th > a";
     public static String tabColmnsBfr = "table > thead > tr > th:nth-child(";
     public static String tabColmnsAft = ")";
     public static String colVisbltyBtn = "a.dt-button.buttons-collection.buttons-colvis>span";
     public static String colVisbility = "body > div.dt-button-collection.fixed.three-column > a > span";
     public static String colVisbilityBfr = "body > div.dt-button-collection.fixed.three-column > a:nth-child(";
     public static String colVisbilityAft = ") > span";     
     public static String colItemsBfr = "div > table > tbody > tr > td:nth-child(";
     public static String colItemsAfr =	") > a";
     public static String colItemNameBfr = "div > table > tbody > tr:nth-child(";
     public static String colItemNameAfr =	") > td:nth-child(";
     public static String pProfile2    = "#personal_profile > div.panel-body > div.row-flex > div:nth-child(2) > table > tbody > tr > th";
     public static String pProfile2Bfr = "#personal_profile > div.panel-body > div.row-flex > div:nth-child(2) > table > tbody > tr:nth-child(";
     public static String pProfile2Aft = ") > th";
     public static String pProfile2DataAft = ") > td";
     
     public static String pProfile3    = "#personal_profile > div.panel-body > div.row-flex > div:nth-child(3) > table > tbody > tr > th";
     public static String pProfile3Bfr = "#personal_profile > div.panel-body > div.row-flex > div:nth-child(3) > table > tbody > tr:nth-child(";
     public static String pProfile3Aft = ") > th";
     public static String pProfile3DataAft = ") > td";
     public static String profSec = "div.toggle-dd-wrapper > ul > li > a";  
     public static String profSecBfr = "div.panel-body > div.toggle-dd-wrapper > ul > li:nth-child(";
     public static String profSecAft = ") > a";
     public static String perInfo = "#tab-profile > div:nth-child(1) > div:nth-child(2) > table > tbody > tr > th";
     public static String perInfoBfr = "#tab-profile > div:nth-child(1) > div:nth-child(2) > table > tbody > tr:nth-child(";
     public static String perInfoAftr =") > th";
     public static String perInfoDataAftr =") > td";
     public static String totalCMP =     "#tab-performance > div:nth-child(2) > div > table > tbody > tr:nth-child(1) > td:nth-child(1)";
     public static String AvgCMP=        "#tab-performance > div:nth-child(2) > div > table > tbody > tr:nth-child(2) > td:nth-child(1)";
     public static String TotalPYAwards= "#tab-performance > div:nth-child(2) > div > table > tbody > tr:nth-child(1) > td:nth-child(2)";
     public static String AvgPYAwards=   "#tab-performance > div:nth-child(2) > div > table > tbody > tr:nth-child(2) > td:nth-child(2)";
     public static String TotalCYAwards= "#tab-performance > div:nth-child(2) > div > table > tbody > tr:nth-child(1) > td:nth-child(3)";
     public static String AvgCYAwards=   "#tab-performance > div:nth-child(2) > div > table > tbody > tr:nth-child(2) > td:nth-child(3)";
     public static String filterOp =  "a.collapsed.btn.btn-default.skip-dirty > div";
     public static String outputFilterOptions = "section.output-section > div.row > div";
     public static String outputFilterOpBfr = "section.output-section > div.row > div:nth-child(";
     public static String outputFilterOpAfr =  ")>div>div:nth-child(2).form-group.boolean.optional.reports_report_output_columns_is_selected>div>label";           //) > div.form-group.boolean.optional.reports_report_output_columns_is_selected > div > label";
     public static String filterChkBox = " > input";
     public static String runReport = "div > div.text-right >input.btn.btn-primary";
     public static String filterAchievementTitleBetween  = "select#reports_report_constraint_columns_attributes_4_default_where_value_begin";
     public static String generationFrom = "#reports_report_constraint_columns_attributes_4_default_where_value_begin";
     public static String generationTo =   "#reports_report_constraint_columns_attributes_4_default_where_value_end";
     public static String advancedFilterOpt = "div.panel-tools > div > a.btn.btn-default.skip-dirty.collapsed > div";
     public static String runReports = "#collapse1 > div.text-right > a.btn.btn-primary.btn-re-run";
     //07/10/2016   Ravi
     
     public static String editFName = "tr:nth-child(1) > td:nth-child(2) > a";
	 public static String markets = "div.col-md-2.text-center.choose_market > a > p";
     public static String wlItemAft = ") > td:nth-child(3)";
     public static String wlReoveAft  = ") > td:nth-child(5) > a.btn.btn-default.btn-xs.remove-from-wishlist";
     public static String cartItemAft = ") > td:nth-child(2) >h4 > a";
     public static String cartDeleteAft = ") > td:nth-child(6) > a > i.ic-icon-regular.ic-icon-trash";
     public static String cartQtyAft = ") > td:nth-child(4) > div >div:nth-child(2) > input";
     public static String cartIncQtyAft = ") > td:nth-child(4) > div >div:nth-child(3)";
     public static String cartDescQtyAft = ") > td:nth-child(4) > div >div:nth-child(1)";
     public static String writeReview = "a.write_review";
     public static String singleStar = "#new_review > div:nth-child(2) > div > label:nth-child(10)";
     public static String twoStar = "#new_review > div:nth-child(2) > div > label:nth-child(8)";
     public static String threeStar = "#new_review > div:nth-child(2) > div > label:nth-child(6)";
     public static String fourStar = "#new_review > div:nth-child(2) > div > label:nth-child(4)";
     public static String fiveStar = "#new_review > div:nth-child(2) > div > label:nth-child(2)";
     public static String revTitle = "#review_title";
     public static String revComment = "#review_review";
     public static String revSubmit = "#new_review > div.form-actions > input";
     public static String revUpdate = "div.form-actions > button.btn.btn-primary.btn-success";
     public static String prodReviewText = "Awesome prod "+ TestBase.generateRandomString();
     public static String sideMenu = "#sidebar-product > li > a";
     public static String reviewInputText = "input#q_title_cont";
     public static String reviewContentInputText = "input#q_review_cont";
     public static String reviewList = "tr > td:nth-child(1) > a";
     public static String reviewListBfr = "tr:nth-child(";
     public static String reviewListAft = ") > td:nth-child(1) > a";
     public static String reviewUserAft = ") > td:nth-child(4) > a";
     public static String reviewEditAft = ") > td:nth-child(8) > a:nth-child(2)";
     public static String reviewApproveAft = ") > td:nth-child(8) > a:nth-child(1)";
     public static String reviewDeleAft = ") > td:nth-child(8) > a:nth-child(3)";
     public static String userName = "div.special-menu.pull-right > div.pull-right.user-nav > div > a";
     public static String review = "#reviews > div > div:nth-child(4) > div > p";
     public static String reviewCom1 = "#reviews > div:nth-child(";
     public static String reviewCom2 = ") > div:nth-child(4) > div > p";
     public static String price = "div > div.panel-body.product-body > span";
     public static String priceBfr = "div:nth-child(";
     public static String priceAfr = ") > div > div.panel-body.product-body > span";
     public static String titlePrice = "h4.filter-title";
     public static String titlePriceText = "Price Range";
     public static String priceChkBx1 = "#sidebar_products_search > div > ul > li:nth-child(1) > input";
     public static String ccAlerts = "div.alert.alert-danger > ul > li";
     public static String delTitle = "legend.stock-shipping-method-title";
     public static String delTitleTxt = "Today's Order Delivery Method";
     public static String delAutoTitleTxt = "Delivery Method";
     public static String autoshipRadio = "input#cart_form_cart_autoship";
     public static String shipAddTit = "div#shipping.panel.panel-default > legend";
     public static String paymentTitle = "div#payment.panel.panel-default > legend";
     public static String paymentTitleText = "Payment Information";     
     public static String addRemoveColumns = "a#add-remove-columns";
     public static String repAddevent = "form#create-bulk-event-form > a";
    
     /**********************Admin -Ecards**********************************************/
     public static String parentEcard = "Ecard Parent "+TestBase.generateRandomString();
     public static String childEcard = "Ecard Child "+TestBase.generateRandomString();
     public static String mangCatLis = "div.panel-tools.resource-btns > a";     
     public static String catDrops = "#categories_id_container > button > span.button-drop-down-display-text";
     public static String catDropsList = "#categories_id_container > ul > li > a";
     public static String catDropsListBfr = "#categories_id_container > ul > li:nth-child(";
     public static String catDropsListAft = ") > a";
     public static String markDrops = "#market_language_container > button > span.button-drop-down-display-text";
     public static String markDropsList = "#market_language_container > ul > li > a";
     public static String markDropsListBfr = "#market_language_container > ul > li:nth-child(";      
     public static String subDrops = "#subscription_plan_container > button > span.button-drop-down-display-text";
     public static String subDropsList = "#subscription_plan_container > ul > li > a";
     public static String subDropsListBfr = "#subscription_plan_container > ul > li:nth-child(";       
     public static String rankDrops = "#rank_definition_container > button > span.button-drop-down-display-text";
     public static String rankDropsList = "#rank_definition_container > ul > li > a";
     public static String rankDropsListBfr = "#rank_definition_container > ul > li:nth-child(";        
     public static String eSearch = "input#ecard_query";
     public static String newCard = "div.panel-tools.resource-btns > a:nth-child(1)";
     public static String cardSizes = "#sizeModal > div > div > div.modal-body > div > div > a";
     public static String cardSizesBfr = "#sizeModal > div > div > div.modal-body > div > div:nth-child(";
     public static String cardSizesAfr = ") > a > div.caption";
     public static String sizeInfo = "div.modal-dialog.modal-lg > div > div.modal-header>div";
     public static String sizeInfoText = "What card size would you like to create?";
     public static String eName = "input#pyr_crm_ecard_template_name";
     public static String eDescrp = "input#pyr_crm_ecard_template_description";
     public static String eTags="#pyr_crm_ecard_template_tag_list";
     public static String eAllowShare="#pyr_crm_ecard_template_allow_share";
     public static String eSaveAsDraft="";
     public static String ePublish="#new_pyr_crm_ecard_template > div > div.panel-heading > div.panel-tools.resource-btns > input.btn.btn-primary";
     public static String eCatLinkOptins="#category-options > li > a";     
     public static String eAddNewCat  ="div > div.col-md-6.text-right > a:nth-child(1)" ;
     public static String eCatBack  ="div > div.col-md-6.text-right > a:nth-child(2)" ;
     public static String eCatName = "input#pyr_crm_ecard_category_category_name";
     public static String eCatParSelect = "select#pyr_crm_ecard_category_parent_category_id";
     public static String eCardCreate = "#new_pyr_crm_ecard_category > div.actions > input";
     public static String catList ="#index_page > div.row.category.ui-sortable > div > div.col-md-5";
     public static String catListBfr = "#index_page > div.row.category.ui-sortable > div:nth-child(";
     public static String catListAfr = ") > div.col-md-5";
     public static String catListPlus = ") > div.col-md-1.category-toggle > a > i";
     public static String subCatList = ") > div.sub-category.ui-sortable > div > div.col-md-5";
     public static String subCatListBfr = ") > div.sub-category.ui-sortable > div:nth-child(";
     public static String subCatListAfr = ") > div.col-md-5";
     public static String catActionAfr = ") > div:nth-child(4) > div";
     public static String catActionEdit = " > ul > li:nth-child(1)";
     public static String catActionDelete =  " > ul > li:nth-child(2)";
     public static String updateCat = "form > div.actions > input";
     public static String eCatChk="#categories_list > tbody > tr > td";
     public static String eCatChkBfr="#categories_list > tbody > tr:nth-child(";
     public static String eCatChkAft=") > td";
     public static String eCatChkBoxAft=") > td > input";
     public static String eChildCatChkBoxAft=") > td > span > text";
     public static String eChildCatChkBoxAft2=") > td > span > input";
     public static String eChildCatChkBoxAftx="//*[@id='categories_list']/tbody/tr[";
     public static String eChildCatChkBoxAft2x="]/td/text()";
     public static String browseBtn = "input#pyr_crm_ecard_template_thumbnail";
     public static String cardList = "#ecard-manager-body > div.ecard-templates-grid > div > div > div > div > div:nth-child(3) > div.resource-title";
     public static String cardListRows = "#ecard-manager-body > div.ecard-templates-grid > div";
     public static String cardListRowsBfr = "#ecard-manager-body > div.ecard-templates-grid > div:nth-child(";
     public static String cardListRowsAft = ") > div > div > div > div:nth-child(3) > div.resource-title";
     public static String ecardNameBfr = ") > div > div:nth-child(";
     public static String ecardNameAfr = ") > div > div:nth-child(3) > div.resource-title";
     public static String moreOpAft = ") > div > div.more-options > div > button > i";
     public static String moreOpt = ") > div > div.more-options > div > ul.dropdown-menu.right-menu > li > a";
     public static String moreOptBfr = ") > div > div.more-options > div > ul.dropdown-menu.right-menu > li:nth-child(";
     public static String moreOptAfr = ") > a";
     public static String preCardTit = "body > div.workspace-modal.modal.fade.sidebar-none.in > div > div > div.modal-body > div.modal-object > div > div.panel-heading > div.panel-title";
     public static String closeEcard = "body > div.workspace-modal.modal.fade.sidebar-none.in > div > div > div.modal-header > div > div:nth-child(3) > div > div > button > i";	 
     public static String creatorSettingList = "#ecard-creator-tabs > li> a";
     public static String creatorSettingListBfr = "#ecard-creator-tabs > li:nth-child(";
     public static String creatorSettingListAft = ") > a";
     public static String ecardUpload = "#cardcreator-backgroundgalleryPanel > a > i.ic-icon-upload";
     public static String dragUpload = "#dropzone_template > div > span";
     public static String dragUploadClose =  "#templateModal > div > div > div.modal-header > button.close";
     public static String BGImage = "#cardcreator-backgroundgallery > div > div > img";
     public static String BGImageBfr = "#cardcreator-backgroundgallery > div > div:nth-child(";
     public static String BGImageAft = ") > img";
     public static String imgGal = "#cardcreator-imagegallery-wrappercontainer > div > img";
     public static String imgGalBfr = "#cardcreator-imagegallery-wrappercontainer > div:nth-child(";
     public static String imgGalAft = ") > img";
     public static String profileAvatar = "#pyr_core_profile_avatar";
     public static String changeCoverPic = "#pyr_core_profile_cover_photo";
     public static String photoSubmit =  "input.btn.btn-primary.pull-right";
     public static String saveButton = "#updateProfilePhotoModal > div > div.modal-content > div.modal-footer > button";
  //   public static String saveButton = "#updateProfilePhotoModal > div > div.modal-content > div.modal-footer > button";
     public static String pubNow = "input#publish_now";
     public static String ecardStatus = "pyr_crm_ecard_template_template_status";

 /****************************************************Page Manager*********************************************************/
     
     public static String adminUserDrpdwn ="#admin_tools_cms_options_container > button";
     public static String keysClose = "#cms-inline-dock > div.panel.panel-default.admin_element.maximized > div.panel-heading > div > div > i.ic-icon-regular.ic-icon-delete";
     public static String keysCollapse = "#cms-inline-dock > div.panel.panel-default.admin_element.maximized > div.panel-heading > div > div > i.ic-icon-regular.ic-icon-collapse";
     public static String marketDropDown = "#admin_tools_cms_market_language_id_container > button > span.button-drop-down-display-text";
     
     /* ***************************************************AVON Stage2 locators******************************************************************** */     
     
     // Contact Manager	       
     public static String gmailPwd_text1 = "ICt3st3r";
     
     public static String gmailId_text2 = "icentris.vibe002@gmail.com";
     public static String gmailPwd_text2 = "ICt3st3r";
     
     public static String gmailId_text3 = "icentris.vibe003@gmail.com";
     public static String gmailPwd_text3 = "ICt3st3r";
          public static String avbtnCreateContactNote = "#new_note_form > div > div> input.btn.btn-primary";
          public static String avActivitiesNotes = "#pyr_core_note_dsm_activities";
          public static String avcontactFirstName_text = "Joyce";
          public static String avcontactLastName_text = "Associates";
          public static String joyceAssocClk = "//*[@id='all-contacts-list']/div[1]/form/div/div[@class='media-body']/div/div[2]/h4/div/a/span[2]";
          public static String joyceAssocContact = "//*[@id='all-contacts-list']/div[1]/form/div/div[@class='media-body']/div/div[2]/h4/div/a/span[2]";
          public static String ac ="#contact-details > div:nth-child(6) > div:nth-child(1) > div:nth-child(1)>div";
          public static String avtitle ="#contact-details > div:nth-child(6) > div:nth-child(3) > div:nth-child(1)>div";
          public static String lang ="#contact-details > div:nth-child(6) > div:nth-child(4) > div:nth-child(1)>div";
          public static String rps ="#contact-details > div:nth-child(6) > div:nth-child(5) > div:nth-child(1)>div";
          public static String repid ="#contact-details > div:nth-child(6) > div:nth-child(1) > div:nth-child(2)>div";
          public static String pclevel ="#contact-details > div:nth-child(6) > div:nth-child(3) > div:nth-child(2)>div";
          public static String loa ="#contact-details > div:nth-child(6) > div:nth-child(4) > div:nth-child(2)>div";
          public static String accntStatusDropdown = "avon_filter_form_account_status";
          public static String representativeId = "avon_filter_form_representative_id";
          public static String titleRank = "avon_filter_form_rank";
          public static String rankPClevel = "avon_filter_form_pc_level";
          public static String languageDrpdn = "avon_filter_form_language";
          public static String filterBtn = ".btn.btn-primary.pull-right";
          public static String avContactFirstName_text = "test";
          public static String avContactLastName_text = "contact";
          public static String avDivisionManager = "division manager";
          public static String avDistrictManager = "district manager"; 
          public static String avaccountStatus = "#avon_filter_form_account_status";
          public static String avDvm="PAT HABERMAN <divisionmanager@avonusa.com>";
          public static String avDsm="NATHALIE QUANT <districtmanager@avonusa.com>";
          public static String repEmailid = "VONDELL D MCKENZIE <Leader@avonusa.com>";
          public static String repunMaskedEmail = "icentris.qa6+02857271@gmail.com";
          public static String dvmunMaskedEmail = "icentris.qa6@gmail.com";
          public static String dsmunMaskedEmail = "icentris.qa6@gmail.com";
          
         
        
          

          
     //ResourceLibrary
          
          public static String[] avrankdeflist = {"Silver Executive Leader","Executive Leader"," Gold Leader"};
          public static String[] avsubplanslist = {"Vibe Lite - Monthly","District Manager","Vibe Pro - Monthly","Vibe Pro - Yearly","Manager Plan"};

     // widgets 
          
          public static String avcontactCount = "//*[@id='user-control-panel']/div[2]/div/a[1]/div";
          public static String avcontactIcon = "//*[@id='user-control-panel']/div[2]/div/a[1]";
          public static String avcalendarIcon = "//*[@id='user-control-panel']/div[2]/div/a[2]";
          
          public static String avtasksIcon  = "//*[@id='user-control-panel']/div[2]/div/a[3]";
          public static String avContacts = "//*[@id='list-results']/div[2]/a";
          public static String avCalendartitle =  "//*[@id='calendar']/div[1]/div[1]";
          
          
          public static String joinBtnn = "//*[@id='event_response_container']/a[1]";
          public static String eventSubjectMail_Text= newEventName_text+"Inviting the Guest through Calendar Event.";
        
        
        
        //Ravi - Avon Ecards/////
          public static String avondivisionManager = "division manager";
          public static String avondistrictManager = "district manager";
        public static String inboxField = ".cf.ix>tbody>tr>td";
        public static String repID = "02683004";
        public static String dvm = "309";
        public static String dsm = "7169";
        public static String pwd = "avonrocks1";
        
        public static String AVeCatChk="ul#categories_list > li";
        public static String AVeCatChkBfr="#categories_list > li:nth-child(";
        public static String AVeCatChkAft=") > ul";
        public static String AVeCatChkBoxAft=") > input";
        public static String hisList="#main-content > div > div.col-md-3.panel-column > ul > li > a";
        public static String hisListBfr="#main-content > div > div.col-md-3.panel-column > ul > li:nth-child(";
        public static String hisListAfr=") > a";
        public static String hisCardrows ="#main-content > div > div.col-md-9.panel-content > div > div.ecards-body > div.row";
        public static String hisCardrowsBfr ="#main-content > div > div.col-md-9.panel-content > div > div.ecards-body > div:nth-child(";
        public static String hisCardrowsAft =")";
        public static String userCaret = "div.collapse.navbar-collapse > ul#special-menu > li.special-user";
   	 public static String userAccount = "#special-menu > li.special-user.open > ul > li:nth-child(1) > a";
        //Avon Reports
   	 public static String headTit = "//table[@id='reportTable1']/thead/tr[1]/th";
   	 public static String headTitBfr = "//table[@id='reportTable1']/thead/tr[1]/th[";
   	 public static String headTitAft = "]/a";
   	 public static String conatctButton = "#main-content > div > div.panel-heading > div.panel-tools > div > span:nth-child(2) > button";
   	 public static String conatctDP = "#contact_dropdown_menu>li>a";
   	 public static String bulkSendMail = "#send-bulk-email-modal > div > div.modal-content > div.modal-body > form > div > input.btn.btn-primary";
        //Avon Notifications
   	 
   	 public static String avonNotiIcon = "#special-menu > li.special-alerts > a > div";
   	 public static String avonPrityList= "ul#priority-list > li > a";
        public static String avonPrityList1 = "ul#priority-list > li:nth-child(";
        public static String avonPrityList2 = ") > a";
        public static String progReportWidget = "//*[@id='source_pyr_tree_widgets_progress_report_main']/span";
        public static String businessDashWidget = "//*[@id='source_application_widgets_kpi_main']/span";
   	 public static String dragLocation = "div#main-content.main-content-column.ui-sortable.edit-wrapper";
   	 public static String widTitles = "div.panel-heading > div.panel-title";	 
   	 public static String downTitle = "div.row > div.col-md-6.panel-title";
   	 public static String actId = "div.col-sm-10.col-xs-10.user-info>span:nth-child(3)";
   	 public static String repName = "p.display-user-name > a";
   	 public static String repEmail = "div.fluid-width > a";
   	 public static String repRank = "div.col-md-6.current_rank > span";
   	 public static String ldShipTitle = "#tab-profile > div:nth-child(1) > div:nth-child(3) > table > tbody > tr:nth-child(4) > td";
   	 public static String camp = "select#team_sales_and_orders_select";
   	 public static String campOp = "select#team_sales_and_orders_select > option";
   	 public static String acLink = "#social-links-list > ul > li > a";
   	 public static String acLinkBfr = "#social-links-list > ul > li:nth-child(";
   	 public static String acLinkAft = ") > a";
   	 public static String contName = "div.contact-display-name";   	 

   	 //Calender
   	 public static String calSerach = "#calendar > div.fc-toolbar > div.fc-right > div > div.input-group > span > button > i";
   	 public static String resLink = "div.modal-header > div > div:nth-child(3) > div > div.controls-container > a";
   	 
   	 /**************************************************MONAT*********************************************************************************/
   	//Contact Manager
   	 
   	public static String monatlnkStartCallScript = "//*[@id='contact-actions']/div[3]/a/span[2]";
		 public static String monatlnkNotes = "//*[@id='contact-actions']/div[4]/a/span[2]";
		 public static String monatlnkSendMesg = "//*[@id='contact-actions']/div[2]/a/span[2]";
		 public static String monatlnkCreateEvent = "//*[@id='contact-actions']/div[6]/a/span[2]";
		 public static String monatlnkCreateTask = "//*[@id='contact-actions']/div[5]/a/span[2]";
		 public static String monatlnkViewHistory = "//*[@id='contact-actions']/div[8]/a/span[2]";
		 public static String monatlnkAdd2Group = "//*[@id='contact-actions']/div[9]/a/span[2]";
		 public static String monatbtnCreateContactNote = "//*[@id='new_note_form']/div[2]/input";
   		 
   	//ResourceLibrary	 
   	 public static String monatlikesCount = "//*[@id='page']/div[9]/div/div/div[2]/div[2]/div[2]/div[1]/section[2]/div/span[3]/div";
   	 public static String lpgnlikesCount = "//*[@id='page']/div[8]/div/div/div[2]/div[2]/div[2]/div[1]/section[2]/div/span[3]/div";
   	 public static String monatsharesCount =  ".badge.badge-4";
   	 public static String noNot = "div.notifications-list > div.alert.alert-warning";
   	 public static String storeUS = "navigation > header > section > subnavigation > div > div.logoLarge > a";
   	 public static String tabsList = "section > subnavigation > div > div.navLarge > ul > li > a";
   	 
   	 
   	 // People
   	 
   	 public static String pplList = "#people_list > div > div.people > div > div > div.media-body";
   	 public static String pplListBfr = "#people_list > div > div.people > div:nth-child(";
   	 public static String pplListAft = ") > div > div.media-body > h4 > a";
   	 public static String chatAft = ") > div > div.media-right.follow-actions > div:nth-child(2) > span > a";
   	 public static String chatFollw = ") > div > div.media-right.follow-actions > div > a:nth-child(1)";
   	 public static String chatFollwStatus = "div.profile-actions-container > a:nth-child(1)";
   	 public static String filterSearch = "div#pyr_community_people_search_form_filter_container";
   	 public static String filterDown =  "div#pyr_community_people_search_form_filter_container > ul > li";
   	 public static String filterDownBfr =  "div#pyr_community_people_search_form_filter_container > ul > li:nth-child(";
   	 public static String filterDownAft =  ") > a";
   	public static String blockUsr = ") > div > div.media-right.follow-actions > div > a:nth-child(2)";
   	 // Usres
   	 
   	 public static String userUpdateBtn = "div.control-group > input.btn.btn-default.btn.btn-primary";
   	 public static String chatText = "form#dock-chat-form > div > textarea";
   	 public static String usersLis ="ul#recently-chatted > li > ul > div.chat-group > li > a > span.user-name";
   	 public static String usersLisBfr ="ul#recently-chatted > li > ul > div.chat-group > li:nth-child(";
   	 public static String usersLisAft =") > a > span.user-name";
   	 
   	 //new
   	 
   	 public static String composeBody = "//iframe[contains(@title,'Rich Text Editor')]";
   	 public static String composeBodyMsg = "//div[contains(@title,'Rich Text Editor, pyr_crm_ecard_message')]";
   	 public static String composeFooter = "//div[contains(@title,'Rich Text Editor, pyr_crm_ecard_footer')]";
   	 public static String composeBodyText = "QA Texted with words"+ TestBase.generateRandomString();
   	 public static String newsThumb= "input#pyr_core_company_news_thumbnail";
   	 public static String newsPost = "input#pyr_core_company_news_file";
   	 public static String compBodyMsg = "div.form-group.text.optional.pyr_crm_ecard_message > div.cke_textarea_inline.cke_editable.cke_editable_inline.cke_contents_ltr.cke_show_borders > br";
   	
   	 public static String  uploaded = "form > input#message-attach-file-field";
//	 public static String monatranker = "Senior Executive Director";
   	public static String monatranker = "Senior Director";
   	
 // ENrollment
   	public static String usMarket = "United States";
   	public static String monatuserName = "#pyr_core_v2_enrollment_username";
   	public static String monatUserNameEnroll = "MOnat"+TestBase.random();
   	public static String monatEnrollmentContinue = "//*[contains(@id,'edit_pyr_core_v2_enrollment_')]/div/a";	
   	public static String monatEnrollmentContinueStep3 = "//*[contains(@id,'edit_pyr_core_v2_enrollment_')]/div/a";	
   	public static String enrollmentCompName = "//*[@id='pyr_core_v2_enrollment_company_name']";
   	public static String enrollmentlanguagePreference = "#pyr_core_v2_enrollment_language_preference";
   	public static String enrollmentShippingAddress1  = "#pyr_core_v2_enrollment_tmp_shipping_address_address1";
	public static String enrollmentShippingzipcode  = "#pyr_core_v2_enrollment_tmp_shipping_address_postal_code";
	public static String enrollmentShippingCity  = "#pyr_core_v2_enrollment_tmp_shipping_address_city";
	public static String enrollmentShippingState = "#pyr_core_v2_enrollment_tmp_shipping_address_state";
	public static String enrollmentShippingCountry = "#pyr_core_v2_enrollment_tmp_shipping_address_country";
	public static String enrollmentShippingAddressSave = "a.btn.btn-primary.save-address";
	public static String enrollmentCreditcardName = "#pyr_core_v2_enrollment_tmp_credit_card_card_name";
	public static String enrollmentCreditcardNumber = "#pyr_core_v2_enrollment_tmp_credit_card_card_number";
	public static String enrollmentCreditcardcvv= "#pyr_core_v2_enrollment_tmp_credit_card_card_cvv";
	public static String enrollmentCreditcardMonth= "#pyr_core_v2_enrollment_tmp_credit_card_card_expire_month";
	public static String enrollmentCreditcardYear= "#pyr_core_v2_enrollment_tmp_credit_card_card_expire_year";
	 //**************************************** ID LIFE *******************************************************************//

	 public static String txtCouponCode = "//*[@id='pyr_crm_coupon_form']/a/img";
	 public static String endsAt = "//*[@id='pyr_crm_coupon_ends_at']";
	 public static String couponMessage = "//*[@id='pyr_crm_coupon_user_message']";
	 public static String couponRecipientsTo = "//*[@id='recipients-to']";
	 public static String ddlRecipient = ".media-body.text-left";
	// public static String btnSendCoupon = ".no-gutter.coupon-link > button";
	 public static String btnContinueCoupon = "//*[@id='pyr_crm_coupon_form']/input[3]";
	 public static String btnAlertOK = "html/body/div[5]/div/div/div[2]/button";
	 public static String btnSendCoupon = "div.col-md-3.coupon-link > button";
	 
	// LPG Enviroment
	 
	 public static String lpg_mangCat = "div.panel-tools.resource-btns > a:nth-child(5)";

	 // IDLife Stage2 
	 public static String idlLikesCount = "//*[@id='page']/div[8]/div/div/div[2]/div[2]/div[2]/div[1]/section[2]/div/span[3]/div";
	 public static String IDLSubPlanLite = "Lite";
	 public static String IDLSubPlanPro = "Pro Bundle";
	 public static String idlBtnCreateUser = "//*[@id='user-create-modal']/div/div[1]/div[3]/button";
	 public static String idlBtnMoreOptions = "//*[@id='event_form']/div[4]/div/div/a[2]";
	 public static String idlife2_enrollCompnayName_text = "iCentris";
     public static String idlife2_enrollFirstName_text = "Jayadev";
     public static String idlife2_enrollLastName_text = "Mootha";
     public static String savedReport_text = "Savedreport" + TestBase.generateRandomNumberInRange(1, 1000);
	 public static String expectedMessage = "Report export complete. The file will be downloaded shortly.";
	 public static String saveReportButton = "#collapse1 > div > a.btn.btn-default.skip-dirty.save-report-button";
	 public static String expectedTitle = "#myplan-title";
	 public static String saveReportName = "#saved_report_name";
	//Reports
	 
     public static String rowResultsCount = "div > table > tbody > tr> td:nth-child(4)";
     public static String outputFields = "div > div > div > div > label.boolean.optional.btn.btn-default.btn-primary.control-label";
     public static String saveReportDescription = "#saved_report_description";
     //IDnutrition

	 public static String newAssessmentBtn  = "new-assessment-button";

	 public static String IDLVUser = "jared";
     public static String IDLVPwd = "12345";    
     public static String moreQuantAuto = "div.add-to-autoship.add-to-cart> div > div > div.number-spinner-more";
     public static String shedDeleBtn = "button.btn.btn-default.btn-add-to-autoship";

	//ecards
	 public static String inputEmailEcard =  "input#recipients-to";
	 public static String ecardMailSendBtn= "a.btn.btn-primary.btn-sm";
	 //shopping
	 public static String qtyField = "input#quantity2";
	 public static String addTocartMonat = "div > button#add-to-cart-button2";
	 public static String shippingAddsec = "div#shippingaddresses";  
	 //profile
	 public static String saveBtnOfCover = "div > div.modal-content > div.modal-footer > button";
	 //News
	  public static String newsTitleBfr = "div:nth-child(";
	  public static String newsTitleAfr = ") > div.media-body > div.content";
	  public static String newsWidTitleBfr = "div#company_news > div:nth-child(";
	  public static String newsWidTitleAfr = ") >div.media-body > h4 > a";
	  
	 
	 //Array String  Data	 
	 public static String[] subProMonthlyUs = {"Pro - Monthly - US"} ; // "Vibe Pro - Monthly"; 
   	 public static String[] subProMonthlyCA = {"Pro - Monthly - CA"} ;
   	 public static String[] monatrankdeflist = {"Market Partner", "Senior Executive Director","Executive Director","Associate Executive Director"};
   	 public static String[]  lpgnrankdeflist = {"Silver Director"};
   	 public static String[] avonRankDefList = {"Non-Ldsp","Executive Leader", "Gold Executive Leader"};
   	 public static String[] idl_enrollProfileConfigFields={"Company name","First name","Last name"}; // IDLife-stage2
   	 public static String[] outputList = {"Level","Associate ID","First name","Last name","Anniversary month"};
   	 public static String[] subscripListOfMonat = {"Pro - Monthly - CA", "Pro - Monthly - US"};
  	 public static String[] subscripListOfVibe = {"Vibe Pro - Yearly", "Vibe Pro - Monthly"};
  	 public static String[] subscripProMonthly = {"Vibe Pro - Monthly"};  	 	 
  	 public static String[] monatsubplanslist = {"Retail Customer","Pro - Monthly - US","Pro - Monthly - CA","VIP Customer"};
  	 public static String[] eventOptions = {"Share","Edit","DeleteEvent"};
  	 public static String[] deleteResources = {newResourceTitle_text9,newResourceTitle_text8};
  	 public static String subProYearly = "Vibe Pro - Yearly";
  	 public static String[] subProBundle = {"Pro Bundle"};
  	 public static String[] subLite = {"Lite"};
 	 public static String[] subProMonthly = {"Vibe Pro - Monthly"};
 	 public static String[] subProYear= {"Vibe Pro - Yearly"};
	 public static String[] subs1 = {"All","Vibe Pro - Monthly","Vibe Pro - Yearly","Vibe Lite - Monthly","Vibe Lite - Yearly"};
	 public static String[] Monatsubs1 = {"Pro - Monthly - US"};
	 public static String seriesWithDependency1[] = {seriesName1};
   	 public static String seriesWithDependency2[] = {seriesName1,seriesName2};
	 public static String trainingWithDependency1[] = {trainingTitle};
   	 public static String trainingWithDependency2[] = {trainingTitle,trainingTitle2};	 
     public static String[] marketslanglist = {"US (English)","US (Spanish)"};
	 public static String[] subplanslist = {"Vibe Pro - Monthly","Vibe Pro - Yearly","Platinum (US)"}; //for master {"Vibe Lite - Monthly","Vibe Lite - Yearly"};
	 public static String[] idLifeSubPlansList = {"Pro Bundle","Pro Promotion"};
	 public static String[] lpgnSubPlansList = {"Vibe Pro - Monthly","Vibe Pro - Yearly"};
	 public static String[] arrSecurityGrps = {"admin","lite"};
	 public static String[] languageList= {"US (English)"};  
	 public static String[] languageList2= {"JP (Japanese)","CA (English)"};	 
	 //0201 Ravi
	 public static String accounts = "ul.dropdown-menu.right-menu > li > a:nth-child(1)";
	 
	 //Page Manager
	 public static String pmTitle = "div.panel-title";
	 public static String pmTitletext ="Page Manager"; 
	 public static String pmAdminUser="button.btn.dropdown-toggle.btn-default"; 
	 public static String pmAdmUserDP = "#admin_tools_cms_options_container > ul > li > a";
	 public static String pmTools = "button.btn.dropdown-toggle.btn-default";
	 public static String pmContentBtn = "#admin_tools_cms_content_set_id_container > button";
	 public static String pmContent = "#admin_tools_cms_content_set_id_container > ul > li > a";
	 public static String pmSiteInher ="#menu_info";
	 public static String pmSiteInher1 ="#menu_info > span";
	 public static String pmSiteInherText ="Site map is currently inheriting:";
	 public static String pmSiteInherText2 ="BASE Global en";
	 
	 public static String pmLangs ="#admin_tools_cms_market_language_id_container > ul > li > a";
	 public static String pmLangsBfr ="#admin_tools_cms_market_language_id_container > ul > li:nth-child(";
	 public static String pmLangsAfr =") > a";
	 
	 //shop
	 
	 public static String autoDatePicker = "div.input-group.date.date_picker > span > button > span";
	 public static String nextMonth = "th.next > span.glyphicon.glyphicon-chevron-right";
	 public static String nxtBfr="thead > tr:nth-child(";
	 public static String nxtAfr=") > th.next > span.glyphicon.glyphicon-chevron-right";
	 public static String nextDate = "//ul/li[1]/div/div[1]/table/tbody/tr[3]/td[7]"; 

// FAQ aDMIN
	 
	 public static String faqAddCategoryBtn = "#main-content > div > div > div.panel-tools > a.btn.btn-primary";
	 public static String faqcreateCategoryBtn = "#main-content > div > div > div.panel-tools > a.btn.btn-default";
	 public static String faqenterCategoryName = "#pyr_core_faq_category_name";
	 public static String faq_CategoryName = "faqcategory "+TestBase.generateRandomNumberInRange(100, 1000);
	 public static String faqenterCaterogyDescription = "#pyr_core_faq_category_description";
	 public static String faq_CategoryDescription = "this is faq description "+TestBase.generateRandomNumberInRange(100, 1000);
	 public static String faqmarkAll = "input#market_all";
	 public static String faqLangDD = "#market_language_checkboxes > div > span > label";//"#market_languages > div > span > label > input";
	 public static String[] faqlanguages= {"US (English)"};
	 public static String[] subProMonthlyTupper = {"Basic (US)", "Plus (US)"};
	 
	 
/********************************************************Tupperware*****************************************************************************************/	 
	 
//Training
    public static String visibilitySettingsIcon = "//*[@id='new_pyr_core_training_video']/div/div[3]/div[1]/div ";
    public static String marketsegmentsCheckboxes = "//*[@id='visibilty_settings']/div[1]/div[2]/div/span";
    public static String regionNumberCheckboxes = "//*[@id='visibilty_settings']/div[2]/div[2]/div/span";
    public static String eliteStatusesCheckboxes = "//*[@id='visibilty_settings']/div[3]/div[2]/div/span";
    public static String lengthOfTimeCheckboxes = "//*[@id='visibilty_settings']/div[4]/div[2]/div/span";
    public static String[] marketsegments = {"nil","Hispanic"}; 
    public static String[] regionNumbersli = {"11 (US)","12 (US)","13 (US)","31 (US)","32 (US)","33 (US)","40 (US)","50 (US)","51 (US)","52 (US)","53 (US)","19 (CA)","39 (CA)"};
    public static String[] eliteStatuses = {"Inactive Elite", "Former Elite","Elite","Bronze","Silver","Gold","Diamond","Platinum","Presidential","Executive Business Leader","Silver Executive Business Leader","Gold Executive Business Leader","Diamond Executive Business Leader","Platinum Executive Business Leader","Presidential Executive Business Leader"};
    public static String[] lengthOfTime= {"0-30 days","31-60 days","61-90 days","91+ days"};

	//ecards
		 public static String ecardTempTitle = "div.panel-heading > div.panel-title";
		 //Patry Plus
		 public static String appTabs = "ul.nav.navbar-nav > li";
		 public static String appTabsBfr= "ul.nav.navbar-nav > li:nth-child(";
		 public static String appTabsAft = ") > a > span:nth-child(2)";
		 public static String pTitle = "h1#label-for-party_plus";
		 public static String pClose = "div.party-plus-container > div#party_plus > div.modal-dialog > div.modal-content >  div.modal-header.clearfix > button.close > i.ic-icon-regular.ic-icon-close";
 
		 // Calendar 2.0
		 
		 public static String dpdnCalendarType = "//*[@id='pyr_crm_event_calendar_type_id']";
		 public static String calendarType_text = "Personal Business Calendar";
		 public static String newEventMoreotions = "//*[@id='event_form']/div[5]/button[1]";
		 public static String calendarEventDescription_text = newEventName_text + " description";
		 public static String calendarEventLocation_text = "New Jersey";
		 public static String calendarEventAddr1_text = "Trumph Towers, Newyork, USA";
		 public static String calendarEventImg = "//*[@id='dropzone_image']";
		 public static String calendarInvitee_text = "Test Contact";
		 public static String chkAllday = "#pyr_crm_event_all_day";
		 public static String chkRepeat = "#pyr_crm_event_repeat_field";
		 
		 //rl2 - 03302017
		 
		 public static String rlResourceRows = "div#index_page >div>div.row";
		 public static String rlResourceRowBfr = "div#index_page >div> div:nth-child(";
		 public static String rlResourceRowAft = ")";
		 public static String rlResourceList = " > div > div > div > div:nth-child(3) > div:nth-child(1)";
		 public static String rlResourceListBfr = "> div > div:nth-child(";
		 public static String rlResourceListAft = ") > div > div:nth-child(3) > div:nth-child(1)";
		 public static String rlResourceThumbAft = ") > div > div:nth-child(2)";

}

