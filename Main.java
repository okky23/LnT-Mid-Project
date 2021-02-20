import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector; //Bonus karyawan belum di kerjain
							/// BONUS GAJI TIDAK DIMASUKKAN	

public class Main {
	Scanner scan = new Scanner(System.in);
	Random rand = new Random();

	// attribute
	String kode = "", nama = "", jabatan = "", kelamin = "";
	int gaji = 0;

	// Vector declaration
	Vector<String> listNama = new Vector<>();
	Vector<String> listKode = new Vector<>();
	Vector<String> listJabatan = new Vector<>();
	Vector<String> listKelamin = new Vector<>();
	Vector<Integer> listGaji = new Vector<>();

	public Main() {
		menu();

	}

	void menu() {
		int pilih = 0;

		do {
			System.out.println("Aplikasi Data Karyawan");
			System.out.println("=========================");
			System.out.println("1.Insert data karyawan");
			System.out.println("2.View data karyawan");
			System.out.println("3.Update data karyawan");
			System.out.println("4.Delete data karyawan");
			System.out.println("5.Exit\n");
			System.out.print("Pilih Menu[1-5]: ");
			try {
				pilih = scan.nextInt();
			} catch (Exception e) {
				pilih = 0;
				System.out.println("Input harus berupa angka!\n");
			}
			scan.nextLine();

			switch (pilih) {
			case 1:
				insert();

				break;

			case 2:
				view();

				break;

			case 3:
				update();

				break;

			case 4:
				delete();

				break;

			}

		} while (pilih != 5);

	}

	void insert() {
		String alfabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String temp = "" + alfabet.charAt(rand.nextInt(26)) + alfabet.charAt(rand.nextInt(26));
		kode = temp + "-" + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10);

		do {
			System.out.print("Input Nama Karyawan[>= 3]: ");
			nama = scan.nextLine();
		} while (!(nama.length() >= 3));

		do {
			System.out.print("Input Jenis Kelamin[Laki-laki | Perempuan] (Case Sensitive): ");
			kelamin = scan.nextLine();
		} while (!(kelamin.equals("Laki-laki")) && !(kelamin.equals("Perempuan")));

		do {
			System.out.print("Input Jabatan[Manager | Supervisor | Admin] (Case Sensitive): ");
			jabatan = scan.nextLine();
		} while (!jabatan.equals("Manager") && !jabatan.equals("Supervisor") && !jabatan.equals("Admin"));

		switch (jabatan) {
		case "Manager":
			gaji = 8000000;

			break;

		case "Supervisor":
			gaji = 6000000;

			break;

		case "Admin":
			gaji = 4000000;

			break;
		}

		// input ke vector
		listKode.add(kode);
		listNama.add(nama);
		listKelamin.add(kelamin);
		listJabatan.add(jabatan);
		listGaji.add(gaji);

		System.out.println("Berhasil menambahkan karyawan dengan ID " + kode);

	}

	void view() {
		if (listNama.isEmpty()) {
			System.out.println("Tidak ada data!\n");

		} else {
			for (int i = 0; i < listNama.size(); i++) {
				for (int j = i + 1; j < listNama.size(); j++) {
					if (listNama.get(i).compareTo(listNama.get(j)) > 0) {

						// swap
						Collections.swap(listKode, i, j);
						Collections.swap(listNama, i, j);
						Collections.swap(listKelamin, i, j);
						Collections.swap(listJabatan, i, j);
						Collections.swap(listGaji, i, j);

					}

				}
			}

			System.out.println(
					"---------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println(
					"| No         |Kode Karyawan         |Nama Karyawan                   |Jenis Kelamin               |Jabatan                       |Gaji            |");
			System.out.println(
					"---------------------------------------------------------------------------------------------------------------------------------------------------");
			for (int i = 0; i < listNama.size(); i++) {
				System.out.printf("| %10d | %20s | %30s | %26s | %28s | %13d  |\n", (i + 1), listKode.get(i),
						listNama.get(i), listKelamin.get(i), listJabatan.get(i), listGaji.get(i));
			}
			System.out.println(
					"---------------------------------------------------------------------------------------------------------------------------------------------------");

		}

	}

	void update() {
		if (listNama.isEmpty()) {
			System.out.println("Tidak ada data!\n");

		} else {
			view();
			int index = 0;
			do {
				try {
					System.out.print("Input No data yang ingin di update [1-" + listNama.size() + "]: ");
					index = scan.nextInt();
				} catch (Exception e) {
					index = 0;
					System.out.println("Input harus berupa angka!");
				}
				scan.nextLine();
			} while (index < 1 || index > listNama.size());
			insert();

			listGaji.set(index, gaji);
			listJabatan.set(index, jabatan);
			listKelamin.set(index, kelamin);
			listKode.set(index, kode);
			listNama.set(index, nama);

			System.out.println("Berhasil Update Data Karyawan!\n");

		}

	}

	void delete() {
		if (listNama.isEmpty()) {
			System.out.println("Tidak ada data!\n");

		} else {
			view();

			int input = 0;
			do {
				try {
					System.out.print("Input 'No' dari data yang ingin dihapus[1-" + listNama.size() + "]: ");
					input = scan.nextInt();
				} catch (Exception e) {
					input = 0;
					System.out.println("Input harus berupa angka!");
				}
			} while (!(input >= 1 && input <= listNama.size()));

			// delete data
			listGaji.remove(input - 1);
			listJabatan.remove(input - 1);
			listKelamin.remove(input - 1);
			listKode.remove(input - 1);
			listNama.remove(input - 1);

			System.out.println("Data Telah Berhasil Dihapus\n");

		}

	}

	public static void main(String[] args) {
		new Main();
	}

}
