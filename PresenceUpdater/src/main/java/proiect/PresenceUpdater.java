package proiect;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.Sheet;
import com.google.api.services.sheets.v4.model.Spreadsheet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.SwingUtilities;

import org.jdatepicker.impl.UtilDateModel;

@SuppressWarnings("serial")
class DateLabelFormatter extends AbstractFormatter {
	private String datePattern = "yyyy-MM-dd";
	private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

	public Object stringToValue(String text) throws ParseException {
		return dateFormatter.parseObject(text);
	}

	public String valueToString(Object value) throws ParseException {
		if (value != null) {
			Calendar cal = (Calendar) value;
			return dateFormatter.format(cal.getTime());
		}

		return "";
	}
}

public class PresenceUpdater extends GUI {
	private static Sheets sheetsService;

	public static void main(String[] args) throws IOException,
			GeneralSecurityException, ParseException {
		firstInitializationGUI();
		frame.setVisible(true);

		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Credential credential = Google.getCredentials();
					sheetsService = Google.getSheetsService(credential);

					String sheetID = textfield1.getText().substring(
							textfield1.getText().indexOf("/d/") + 3,
							textfield1.getText().lastIndexOf("/"));

					Spreadsheet response1 = sheetsService.spreadsheets()
							.get(sheetID).setIncludeGridData(false).execute();

					List<Sheet> workSheetList = response1.getSheets();

					List<String> sheetsNames = new ArrayList<String>();

					for (Sheet sheet : workSheetList) {
						sheetsNames.add(sheet.getProperties().getTitle());
					}
					String[] array1 = new String[workSheetList.size()];
					sheetsNames.toArray(array1);

					for (String arrayValue : array1) {
						if (!arrayValue.equals("")) {
							comboBox1.addItem(arrayValue);
						}
					}

					secondInitializationGUI();
					SwingUtilities.updateComponentTreeUI(frame);

					button2.addActionListener(new ActionListener() {

						@SuppressWarnings({ "unchecked", "rawtypes" })
						public void actionPerformed(ActionEvent arg0) {
							try {

								String sheetName = comboBox1.getSelectedItem()
										.toString();
								List<List<Object>> googleSheet = GoogleSheet
										.getGoogleSheet(sheetsService, sheetID,
												sheetName);

								List<Object> headerGoogleSheet = googleSheet
										.get(0);

								String[] array2 = new String[headerGoogleSheet
										.size()];
								headerGoogleSheet.toArray(array2);

								UtilDateModel[] models = { model1, model2,
										model3, model4, model5, model6, model7,
										model8, model9, model10, model11,
										model12, model13 };
								for (UtilDateModel model : models) {
									model.setSelected(true);
								}
								model1.setDate(2021, 2, 1);
								model2.setDate(2021, 2, 8);
								model3.setDate(2021, 2, 15);
								model4.setDate(2021, 2, 22);
								model5.setDate(2021, 2, 29);
								model6.setDate(2021, 3, 5);
								model7.setDate(2021, 3, 12);
								model8.setDate(2021, 3, 19);
								model9.setDate(2021, 3, 26);
								model10.setDate(2021, 4, 10);
								model11.setDate(2021, 4, 17);
								model12.setDate(2021, 4, 24);
								model13.setDate(2021, 4, 31);
								try {
									for (String arrayValue : array2) {
										if (!arrayValue.equals("")) {
											comboBox2.addItem(arrayValue);
											for (JComboBox comboBox : comboBoxes) {
												comboBox.addItem(arrayValue);
											}
										}
									}

									comboBox2.setSelectedIndex(1);
									int i = 4;
									for (JComboBox comboBox : comboBoxes) {
										comboBox.setSelectedIndex(i);
										i++;
									}
								} catch (IllegalArgumentException e) {
									comboBox2.setSelectedIndex(-1);
									for (JComboBox comboBox : comboBoxes) {
										comboBox.setSelectedIndex(-1);
									}
								}

								thirdInitializationGUI();
								SwingUtilities.updateComponentTreeUI(frame);

								button3.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent arg0) {
										try {

											fourthInitializationGUI();
											SwingUtilities
													.updateComponentTreeUI(frame);

											List<String> presenceLists = CSVFiles
													.getPresenceLists(informations);

											int collumnNumber = 0;
											for (Object collumn : headerGoogleSheet) {
												collumnNumber++;
												if (collumn.equals(informations
														.get(0))) {
													break;
												}
											}

											List<Object> studentsGoogleSheet = new ArrayList<Object>();

											try {
												for (List<?> student : googleSheet) {

													studentsGoogleSheet.add(student
															.get(collumnNumber - 1));
												}
											} catch (IndexOutOfBoundsException e) {
											}

											int i = 0;
											for (String studentName : presenceLists) {

												String range = GoogleSheet
														.findRange(
																studentName,
																studentsGoogleSheet,
																headerGoogleSheet);
												if (range != null) {
													try {
														GoogleSheet
																.updateGoogleSheet(
																		range,
																		sheetsService,
																		sheetID,
																		sheetName,
																		informations
																				.get(1));
														i++;
														textarea.setText(i
																+ textarea
																		.getText()
																		.substring(
																				textarea.getText()
																						.indexOf(
																								"data updated") - 1,
																				textarea.getText()
																						.length()));
														textarea.update(textarea
																.getGraphics());
														TimeUnit.SECONDS
																.sleep(1);
													} catch (Exception e) {
														textarea.append("\nDuplicate presence: "
																+ studentName
																		.substring(
																				studentName
																						.indexOf(".") + 1,
																				studentName
																						.length())
																+ " ("
																+ studentName
																		.substring(
																				0,
																				studentName
																						.indexOf("."))
																+ ")");
													}
												} else {
													if (!studentName
															.substring(
																	0,
																	studentName
																			.indexOf("."))
															.equals("")) {
														textarea.append("\nCouldn't find "
																+ studentName
																		.substring(
																				studentName
																						.indexOf(".") + 1,
																				studentName
																						.length())
																+ " in Google Sheets! ("
																+ studentName
																		.substring(
																				0,
																				studentName
																						.indexOf("."))
																+ ")");
													}
												}
											}
											textarea.append("\nClose application pressing the X button!");
										} catch (GeneralSecurityException
												| IOException | ParseException e) {
											textarea.append("\nAn error has occurred. Please restart application!");
										}
									}
								});

							} catch (GeneralSecurityException | IOException e) {
								textarea.append("\nAn error has occurred. Please restart application!");
							}
						}
					});
				} catch (GeneralSecurityException | IOException e) {
					textarea.append("\nAn error has occurred. Please restart application!");
				}
			}
		});
	}
}