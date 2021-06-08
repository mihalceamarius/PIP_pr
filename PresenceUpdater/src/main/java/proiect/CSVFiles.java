package proiect;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

class CSVFiles extends GUI {
	private static List<String> paths = new ArrayList<String>();

	private static void getCSVFiles() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView()
				.getHomeDirectory());
		jfc.setDialogTitle("Select CSV files");
		jfc.setAcceptAllFileFilterUsed(false);
		jfc.setMultiSelectionEnabled(true);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"CSV files", "csv");
		jfc.addChoosableFileFilter(filter);

		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File[] files = jfc.getSelectedFiles();
			Arrays.asList(files).forEach(x -> {
				if (x.isFile()) {
					paths.add(x.getPath().toString());
				}
			});
		}
	}

	protected static List<String> getPresenceLists(
			ArrayList<String> informations) throws IOException,
			GeneralSecurityException, ParseException {
		try {
			getCSVFiles();
		} catch (Exception NullPointerException) {
			textarea.append("Error opening CSV file!");
		}
		List<String> presenceList = new ArrayList<String>();
		List<String> presenceList2 = new ArrayList<String>();
		for (String path : paths) {
			try {
				File file = new File(path);
				FileInputStream fis = new FileInputStream(file);
				InputStreamReader isr = new InputStreamReader(fis,
						StandardCharsets.UTF_8);
				@SuppressWarnings("resource")
				BufferedReader reader = new BufferedReader(isr);
				String str;
				while ((str = reader.readLine()) != null) {
					if (str.contains("Date")) {
						String[] strSplit = str.replace("\"", "").split(", ");
						for (int i = 2; i < informations.size(); i++) {
							SimpleDateFormat formatter = new SimpleDateFormat(
									"yyyy-MM-dd");
							Date date = formatter.parse(strSplit[1]);
							Date dateMin = formatter
									.parse(informations.get(i)
											.substring(
													informations.get(i)
															.indexOf(".") + 2,
													informations.get(i)
															.length()));
							Calendar calendar = Calendar.getInstance();
							calendar.setTime(dateMin);
							calendar.add(Calendar.DAY_OF_MONTH, 6);
							Date dateMax = calendar.getTime();
							if (!date.after(dateMax) && !date.before(dateMin)) {
								presenceList2.add(informations.get(i)
										.substring(
												0,
												informations.get(i)
														.indexOf(".") + 1));
								continue;
							}
						}
					}
					if (str.contains("\u2714")) {
						String[] strSplit = str.replace("\"", "").split(", ");
						String[] strSplitSplit = strSplit[0].split(" ");
						presenceList
								.add(presenceList2.get(presenceList2.size() - 1)
										+ strSplitSplit[1]
										+ " "
										+ strSplitSplit[0]);
					}
				}
			} catch (IOException e) {
				textarea.append("Error reading CSV file!");
			}
		}
		return presenceList;
	}
}