package proiect;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;

class GoogleSheet extends GUI {
	protected static List<List<Object>> getGoogleSheet(Sheets sheetsService,
			String sheetID, String sheetName) throws IOException,
			GeneralSecurityException {
		ValueRange result = sheetsService.spreadsheets().values()
				.get(sheetID, sheetName).execute();
		List<List<Object>> values = result.getValues();
		return values;
	}

	protected static String findRange(String studentName,
			List<Object> studentsGoogleSheet, List<Object> headerGoogleSheet)
			throws IOException, GeneralSecurityException {
		int rangeNumber = 1;
		String range = null;
		for (Object student : studentsGoogleSheet.subList(1,
				studentsGoogleSheet.size())) {
			rangeNumber++;
			String[] studentNameSplit = student.toString().split(" ");
			String studentNameGoogleSheet = studentNameSplit[0] + " "
					+ studentNameSplit[2];
			String studentNamePresenceList = studentName.substring(
					studentName.indexOf(".") + 1, studentName.length());
			String laboratory = studentName.substring(0,
					studentName.indexOf("."));
			int rangeLetter = 64;
			for (Object letter : headerGoogleSheet) {
				rangeLetter++;
				if (letter.equals(laboratory)) {
					if (studentNameGoogleSheet.equals(studentNamePresenceList)) {
						range = String.valueOf((char) rangeLetter)
								+ String.valueOf(rangeNumber);
					}
				}
				continue;
			}
		}
		return range;
	}

	protected static void updateGoogleSheet(String range, Sheets sheetsService,
			String sheetID, String sheetName, String value) throws Exception {
		try {
			ValueRange body = new ValueRange().setValues(Arrays.asList(Arrays
					.asList(value)));
			String sheetRange = sheetName + "!" + range;

			List<List<Object>> getValue = sheetsService.spreadsheets().values()
					.get(sheetID, sheetRange).execute().getValues();
			if (getValue == null) {
				sheetsService.spreadsheets().values()
						.update(sheetID, sheetRange, body)
						.setValueInputOption("RAW").execute();
			} else {
				throw new Exception("Duplicate value!");
			}
		} catch (IOException e) {
			textarea.append("\nUpdate failed!");
		}
	}
}