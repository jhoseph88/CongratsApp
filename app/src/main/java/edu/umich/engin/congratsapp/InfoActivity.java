package edu.umich.engin.congratsapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {
    private final String ONBOARDING_STATUS = "onboardingDone";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(edu.umich.engin.congratsapp.R.layout.activity_info);

        String infoString = "Congrats Michigan Engineer! Mobile Application\n\n" +
                "PRIVACY POLICY FRAMEWORK\n\n" +
                "This privacy policy governs your use of the Congrats Michigan Engineer! software application (“Application”) on a mobile device that was created by The University of Michigan. The\n\n" +
                "Application includes checklist, news, video, and information.\n\n" +
                "What information does the Application obtain and how is it used?\n\n" +
                "Automatically Collected Information - In addition, the Application may collect certain information automatically, such as the type of mobile device you use, your mobile devices unique device ID, the IP address of your mobile device, your mobile operating system, the type of mobile Internet browsers you use, and information about the way you use the Application.\n\n" +
                "This Application does not collect precise information about the location of your mobile device.\n\n" +
                "Do third parties see and/or have access to information obtained by the Application?\n\n" +
                "Yes. We will share your information with third parties, such as Google Analytics, only in the ways that are described in this privacy statement.\n\n" +
                "Google Analytics is a web analysis service provided by Google Inc. (“Google”). Google utilizes the Data collected to track and examine the use of this Application, to prepare reports on its activities and share them with other Google services.\n\n" +
                "Google may use the Data collected to contextualize and personalize the ads of its own advertising network.\n\n" +
                "We may disclose User Provided and Automatically Collected Information:  as required by law, such as to comply with a subpoena, or similar legal process; when we believe in good faith that disclosure is necessary to protect our rights, protect your safety or the safety of others, investigate fraud, or respond to a government request; with our trusted services providers who work on our behalf, do not have an independent use of the information we disclose to them, and have agreed to adhere to the rules set forth in this privacy statement.\n\n" +
                "If University of Michigan is involved in a merger, acquisition, or sale of all or a portion of its assets, you will be notified via email and/or a prominent notice on our Web site of any change in ownership or uses of this information, as well as any choices you may have regarding this information;\n\n" +
                "Opt-out of all information collection by uninstalling the Application – You can stop all collection of information by the Application easily by uninstalling the Application. You may use the standard uninstall processes as may be available as part of your mobile device or via the mobile application marketplace or network.\n\n" +
                "Data Retention Policy, Managing Your Information\n\n" +
                "We will retain User Provided data for as long as you use the Application and for a reasonable time thereafter. If you’d like us to delete User Provided Data that you have provided via the Application, please contact us at CongratsMgineerApp@umich.edu and we will respond in a reasonable time. Please note that some or all of the User Provided Data may be required in order for the Application to function properly, and we may be required to retain certain information by law.\n\n" +
                "Children\n\n" +
                "We do not use the Application to knowingly solicit data from or market to children under the age of 13. If a parent or guardian becomes aware that his or her child has provided us with information without their consent, he or she should contact us at\n\n" +
                "CongratsMgineerApp@umich.edu.\n\n We will delete such information from our files within a reasonable time.\n\n" +
                "Security\n\n" +
                "We are concerned about safeguarding the confidentiality of your information. We provide physical, electronic, and procedural safeguards to protect information we process and maintain. For example, we limit access to this information to authorized employees and contractors who need to know that information in order to operate, develop or improve our Application. Please be aware that, although we endeavor to provide reasonable security for information we process and maintain, no security system can prevent all potential security breaches.\n\n" +
                "Changes\n\n" +
                "This Privacy Policy may be updated from time to time for any reason. We will notify you of any changes to our Privacy Policy by posting the new Privacy Policy here congrats.engin.umich.edu. You are advised to consult this Privacy Policy regularly for any changes.\n\n" +
                "Your Consent\n\n" +
                "By using the Services, you are consenting to our processing of User Provided and Automatically Collection information as set forth in this Privacy Policy now and as amended by us. \"Processing,\" means using cookies on a computer/hand held device or using or touching information in any way, including, but not limited to, collecting, storing, deleting, using, combining and disclosing information, all of which activities will take place in the United States. If you reside outside the U.S. your information will be transferred to the U.S., and processed and stored there under U.S. privacy standards. By using the Application and providing information to us, you consent to such transfer to, and processing in, the US.\n\n" +
                "Contact us\n\n" +
                "If you have any questions regarding privacy while using the Application, or have questions about our practices, please contact us via email at CongratsMgineerApp@umich.edu\n\n" +
                "Legal Disclaimer\n\n" +
                "THE SOFTWARE DESIGNER AND PROVIDER, INCLUDING ANY COLLABORATING INSTITUTION(S), INCLUDING THE UNIVERSITY OF MICHIGAN, SHALL HAVE NO LIABILITY\n\n" +
                "TO ANY PATIENT OR ANY OTHER PERSON. NO SUCH PERSON OR ENTITY ASSUMES ANY LEGAL LIABILITY OR RESPONSIBILITY FOR THE ACCURACY, COMPLETENESS, SUITABILITYOR USEFULNESS OF CONGRATS MICHIGAN ENGINEER! APP OR RELATED INFORMATION. ANY AND ALL LIABILITY ARISING DIRECTLY OR INDIRECTLY FROM THE USE OF THIS APPLICATION IS HEREBY DISCLAIMED. THE INFORMATION HEREIN IS PROVIDED “AS IS” AND WITHOUT ANY WARRANTY EXPRESSED OR IMPLIED, INCLUDING THE WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE.\n\n";

        TextView infoText = (TextView) findViewById(edu.umich.engin.congratsapp.R.id.infoText);
        infoText.setText(infoString);

        String infoPageHeader = "<b>ABOUT & CONTACT<b><br>";
        TextView infoHeader = (TextView) findViewById(edu.umich.engin.congratsapp.R.id.infoHeader);
        infoHeader.setText(Html.fromHtml(infoPageHeader) );

        String appContactEmail = "<a href=\"mailto:CongratsMgineerApp@umich.edu\">" +
                                 "CongratsMgineerApp@umich.edu</a><br>";
        TextView appEmailView = (TextView) findViewById(edu.umich.engin.congratsapp.R.id.appContactEmail);
        appEmailView.setText(Html.fromHtml(appContactEmail) );
        appEmailView.setMovementMethod(LinkMovementMethod.getInstance() );

        String admissionsContactEmail = "<a href=\"mailto:enginrta@umich.edu\">" +
                                        "enginrta@umich.edu</a><br>";
        TextView admissionsEmailView = (TextView) findViewById(edu.umich.engin.congratsapp.R.id.admissionsContactEmail);
        admissionsEmailView.setText(Html.fromHtml(admissionsContactEmail) );
        admissionsEmailView.setMovementMethod(LinkMovementMethod.getInstance() );

        TextView privacyPolicyHeader = (TextView) findViewById(edu.umich.engin.congratsapp.R.id.privacyPolicyHeader);
        String privPolicyTitle = "<b>Privacy Policy<b><br>";
        privacyPolicyHeader.setText(Html.fromHtml(privPolicyTitle) );

        Button backToOnboardingButton = (Button) findViewById(R.id.backToOnboardingButton);
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        backToOnboardingButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(ONBOARDING_STATUS, false);
                editor.apply();
                Intent switchToTabActivity = new Intent(getApplicationContext(), Onboarding.class);
                startActivity(switchToTabActivity);
            }
        });
    }
}
