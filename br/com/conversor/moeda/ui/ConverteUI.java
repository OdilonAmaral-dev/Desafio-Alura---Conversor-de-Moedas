package br.com.conversor.moeda.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import br.com.conversor.moeda.controller.MoedaController;
import br.com.conversor.moeda.model.GuardaMoedas;
import br.com.conversor.moeda.model.Moeda;

public class ConverteUI extends JFrame {

	/**
	 * @author Odilon Amaral - Desafio Alura T5 - Conversor de Moedas
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldValorOrigem, textFieldValorDestino;
	private JLabel lbl1;
	private JComboBox<String> jComboBoxMoedaOrigem, jComboBoxMoedaDestino;
	private JPanel contentPane;
	private JTextArea textArea;
	private JButton btnConverte;
	private JSeparator separator, separator_1;
	GuardaMoedas guardador = new GuardaMoedas();

	public ConverteUI() throws IOException {
		criaInstanciasMoeda();
		initUI();
	}
	

	private void initUI() throws IOException {

		setTitle("Desafio Alura - Conversor de Moedas");
		setSize(800, 600);
		setVisible(true);
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBorder(LineBorder.createGrayLineBorder());
		contentPane.setBackground(Color.LIGHT_GRAY);
		
		lbl1 = new JLabel("Selecione as moedas para conversão");
		lbl1.setFont(new Font("Serif", Font.BOLD, 16));
		lbl1.setBounds(35, 20, 300, 30);
		contentPane.add(lbl1);

		textFieldValorOrigem = new JTextField();
		textFieldValorOrigem.setBounds(35, 61, 54, 23);		
		contentPane.add(textFieldValorOrigem);
		textFieldValorOrigem.setColumns(10);

		textFieldValorOrigem.setText("1.00");
		textFieldValorOrigem.setEditable(false);		contentPane.add(textFieldValorOrigem);

		textFieldValorDestino = new JTextField();
		textFieldValorDestino.setColumns(10);
		textFieldValorDestino.setBounds(35, 99, 54, 23);
		textFieldValorDestino.setText(buscaCotacao("USD-BRL").toString());
		textFieldValorDestino.setEditable(false);
		contentPane.add(textFieldValorDestino);

		separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(101, 84, -13, -21);
		contentPane.add(separator);

		separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(102, 124, -13, -21);
		contentPane.add(separator_1);

		jComboBoxMoedaOrigem.setMaximumRowCount(157);
		jComboBoxMoedaOrigem.setBounds(99, 61, 268, 23);
		jComboBoxMoedaOrigem.setSelectedIndex(138);
		contentPane.add(jComboBoxMoedaOrigem);

		jComboBoxMoedaDestino.setMaximumRowCount(157);
		jComboBoxMoedaDestino.setBounds(101, 99, 268, 23);
		jComboBoxMoedaDestino.setSelectedIndex(17);
		contentPane.add(jComboBoxMoedaDestino);

		textArea = new JTextArea();
		textArea.setBounds(35, 133, 332, 48);
		textArea.setEditable(false);
		textArea.setText("Projeto do Curso de Java - Alura T5   -   Acessa a API AWESOME");
		textArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textArea.setFont(new Font("Serif", Font.BOLD, 10));
		textArea.setAutoscrolls(rootPaneCheckingEnabled);
		contentPane.add(textArea);

		btnConverte = new JButton("Converte");
		btnConverte.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnConverte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//System.out.println("Indice da moeda selecionada - Origem " + jComboBoxMoedaOrigem.getSelectedIndex());
				
				//System.out.println("Indice da moeda selecionada - Destino " + jComboBoxMoedaDestino.getSelectedIndex());				
				
				Moeda moedaOrigem = guardador.getMoeda(jComboBoxMoedaOrigem.getSelectedIndex());
				
				//System.out.println("Guardador retornou - Origem " + moedaOrigem.getIdMoeda());			
				
				Moeda moedaDestino = guardador.getMoeda(jComboBoxMoedaDestino.getSelectedIndex());
				
				//System.out.println("Guardador retornou - Destino " + moedaDestino.getIdMoeda());			
							
				String parmApiAwesome = moedaOrigem.getIdMoeda().toString() + "-" + moedaDestino.getIdMoeda().toString();
				
				Double valorCotacao = 0.00;
				try {
					valorCotacao = buscaCotacao(parmApiAwesome);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				textFieldValorDestino.setText(valorCotacao.toString());
			}
		});
		btnConverte.setBounds(182, 192, 100, 30);
		contentPane.add(btnConverte);
	}

	private Double buscaCotacao(String moeda) throws IOException {		
		MoedaController moedaController = new MoedaController();
		return moedaController.acessaApiAwesomeAtual(moeda);		
	}
	
	private void criaInstanciasMoeda() {
		
		Moeda m1 = new Moeda("AED","Dirham dos Emirados");
		guardador.adiciona(m1);
		
		Moeda m2 = new Moeda("AFN","Afghani do Afeganistao");
		guardador.adiciona(m2);
		
		Moeda m3 = new Moeda("ALL","Lek Albanes");
		guardador.adiciona(m3);
		
		Moeda m4 = new Moeda("AMD","Dram Armenio");
		guardador.adiciona(m4);
		
		Moeda m5 = new Moeda("ANG","Guilder das Antilhas");
		guardador.adiciona(m5);
		
		Moeda m6 = new Moeda("AOA","Kwanza Angolano");
		guardador.adiciona(m6);
		
		Moeda m7 = new Moeda("ARS","Peso Argentino");
		guardador.adiciona(m7);
		
		Moeda m8 = new Moeda("AUD","Dolar Australiano");
		guardador.adiciona(m8);
		
		Moeda m9 = new Moeda("AZN","Manat Azeri");
		guardador.adiciona(m9);
		
		Moeda m10 = new Moeda("BAM","Marco Conversivel");
		guardador.adiciona(m10);
		
		Moeda m11 = new Moeda("BBD","Dolar de Barbados");
		guardador.adiciona(m11);
		
		Moeda m12 = new Moeda("BDT","Taka de Bangladesh");
		guardador.adiciona(m12);
		
		Moeda m13 = new Moeda("BGN","Lev Bulgaro");
		guardador.adiciona(m13);
		
		Moeda m14 = new Moeda("BHD","Dinar do Bahrein");
		guardador.adiciona(m14);
		
		Moeda m15 = new Moeda("BIF","Franco Burundinense");
		guardador.adiciona(m15);
		
		Moeda m16 = new Moeda("BND","Dolar de Brunei");
		guardador.adiciona(m16);
		
		Moeda m17 = new Moeda("BOB","Boliviano");
		guardador.adiciona(m17);
		
		Moeda m18 = new Moeda("BRL","Real Brasileiro");
		guardador.adiciona(m18);
		
		Moeda m19 = new Moeda("BRLT","Real Brasileiro Turismo");
		guardador.adiciona(m19);
		
		Moeda m20 = new Moeda("BSD","Dolar das Bahamas");
		guardador.adiciona(m20);
		
		Moeda m21 = new Moeda("BTC","Bitcoin");
		guardador.adiciona(m21);
		
		Moeda m22 = new Moeda("BWP","Pula de Botswana");
		guardador.adiciona(m22);
		
		Moeda m23 = new Moeda("BYN","Rublo Bielorrusso");
		guardador.adiciona(m23);
		
		Moeda m24 = new Moeda("BZD","Dolar de Belize");
		guardador.adiciona(m24);
		
		Moeda m25 = new Moeda("CAD","Dolar Canadense");
		guardador.adiciona(m25);
		
		Moeda m26 = new Moeda("CHF","Franco Suiço");
		guardador.adiciona(m26);
		
		Moeda m27 = new Moeda("CHFRTS","Franco Suiço");
		guardador.adiciona(m27);
		
		Moeda m28 = new Moeda("CLP","Peso Chileno");
		guardador.adiciona(m28);
		
		Moeda m29 = new Moeda("CNH","Yuan chines offshore");
		guardador.adiciona(m29);
		
		Moeda m30 = new Moeda("CNY","Yuan Chines");
		guardador.adiciona(m30);
		
		Moeda m31 = new Moeda("COP","Peso Colombiano");
		guardador.adiciona(m31);
		
		Moeda m32 = new Moeda("CRC","Colon Costarriquenho");
		guardador.adiciona(m32);
		
		Moeda m33 = new Moeda("CUP","Peso Cubano");
		guardador.adiciona(m33);
		
		Moeda m34 = new Moeda("CVE","Escudo cabo-verdiano");
		guardador.adiciona(m34);
		
		Moeda m35 = new Moeda("CZK","Coroa Checa");
		guardador.adiciona(m35);
		
		Moeda m36 = new Moeda("DJF","Franco do Djubouti");
		guardador.adiciona(m36);
		
		Moeda m37 = new Moeda("DKK","Coroa Dinamarquesa");
		guardador.adiciona(m37);
		
		Moeda m38 = new Moeda("DOGE","Dogecoin");
		guardador.adiciona(m38);
		
		Moeda m39 = new Moeda("DOP","Peso Dominicano");
		guardador.adiciona(m39);
		
		Moeda m40 = new Moeda("DZD","Dinar Argelino");
		guardador.adiciona(m40);
		
		Moeda m41 = new Moeda("EGP","Libra Egipcia");
		guardador.adiciona(m41);
		
		Moeda m42 = new Moeda("ETB","Birr Etiope");
		guardador.adiciona(m42);
		
		Moeda m43 = new Moeda("ETH","Ethereum");
		guardador.adiciona(m43);
		
		Moeda m44 = new Moeda("EUR","Euro");
		guardador.adiciona(m44);
		
		Moeda m45 = new Moeda("FJD","Dolar de Fiji");
		guardador.adiciona(m45);
		
		Moeda m46 = new Moeda("GBP","Libra Esterlina");
		guardador.adiciona(m46);
		
		Moeda m47 = new Moeda("GEL","Lari Georgiano");
		guardador.adiciona(m47);
		
		Moeda m48 = new Moeda("GHS","Cedi Ganes");
		guardador.adiciona(m48);
		
		Moeda m49 = new Moeda("GMD","Dalasi da Gâmbia");
		guardador.adiciona(m49);
		
		Moeda m50 = new Moeda("GNF","Franco de Guine");
		guardador.adiciona(m50);
		
		Moeda m51 = new Moeda("GTQ","Quetzal Guatemalteco");
		guardador.adiciona(m51);
		
		Moeda m52 = new Moeda("HKD","Dolar de Hong Kong");
		guardador.adiciona(m52);
		
		Moeda m53 = new Moeda("HNL","Lempira Hondurenha");
		guardador.adiciona(m53);
		
		Moeda m54 = new Moeda("HRK","Kuna Croata");
		guardador.adiciona(m54);
		
		Moeda m55 = new Moeda("HTG","Gourde Haitiano");
		guardador.adiciona(m55);
		
		Moeda m56 = new Moeda("HUF","Florim Hungaro");
		guardador.adiciona(m56);
		
		Moeda m57 = new Moeda("IDR","Rupia Indonesia");
		guardador.adiciona(m57);
		
		Moeda m58 = new Moeda("ILS","Novo Shekel Israelense");
		guardador.adiciona(m58);
		
		Moeda m59 = new Moeda("INR","Rupia Indiana");
		guardador.adiciona(m59);
		
		Moeda m60 = new Moeda("IQD","Dinar Iraquiano");
		guardador.adiciona(m60);
		
		Moeda m61 = new Moeda("IRR","Rial Iraniano");
		guardador.adiciona(m61);
		
		Moeda m62 = new Moeda("ISK","Coroa Islandesa");
		guardador.adiciona(m62);
		
		Moeda m63 = new Moeda("JMD","Dolar Jamaicano");
		guardador.adiciona(m63);
		
		Moeda m64 = new Moeda("JOD","Dinar Jordaniano");
		guardador.adiciona(m64);
		
		Moeda m65 = new Moeda("JPY","Iene Japones");
		guardador.adiciona(m65);
		
		Moeda m66 = new Moeda("JPYRTS","Iene Japones");
		guardador.adiciona(m66);
		
		Moeda m67 = new Moeda("KES","Shilling Queniano");
		guardador.adiciona(m67);
		
		Moeda m68 = new Moeda("KGS","Som Quirguistanes");
		guardador.adiciona(m68);
		
		Moeda m69 = new Moeda("KHR","Riel Cambojano");
		guardador.adiciona(m69);
		
		Moeda m70 = new Moeda("KMF","Franco Comorense");
		guardador.adiciona(m70);
		
		Moeda m71 = new Moeda("KRW","Won Sul-Coreano");
		guardador.adiciona(m71);
		
		Moeda m72 = new Moeda("KWD","Dinar Kuwaitiano");
		guardador.adiciona(m72);
		
		Moeda m73 = new Moeda("KYD","Dolar das Ilhas Cayman");
		guardador.adiciona(m73);
		
		Moeda m74 = new Moeda("KZT","Tengue Cazaquistanes");
		guardador.adiciona(m74);
		
		Moeda m75 = new Moeda("LAK","Kip Laosiano");
		guardador.adiciona(m75);
		
		Moeda m76 = new Moeda("LBP","Libra Libanesa");
		guardador.adiciona(m76);
		
		Moeda m77 = new Moeda("LKR","Rupia de Sri Lanka");
		guardador.adiciona(m77);
		
		Moeda m78 = new Moeda("LSL","Loti do Lesoto");
		guardador.adiciona(m78);
		
		Moeda m79 = new Moeda("LTC","Litecoin");
		guardador.adiciona(m79);
		
		Moeda m80 = new Moeda("LYD","Dinar Libio");
		guardador.adiciona(m80);
		
		Moeda m81 = new Moeda("MAD","Dirham Marroquino");
		guardador.adiciona(m81);
		
		Moeda m82 = new Moeda("MDL","Leu Moldavo");
		guardador.adiciona(m82);
		
		Moeda m83 = new Moeda("MGA","Ariary Madagascarense");
		guardador.adiciona(m83);
		
		Moeda m84 = new Moeda("MKD","Denar Macedônio");
		guardador.adiciona(m84);
		
		Moeda m85 = new Moeda("MMK","Kyat de Mianmar");
		guardador.adiciona(m85);
		
		Moeda m86 = new Moeda("MNT","Mongolian Tugrik");
		guardador.adiciona(m86);
		
		Moeda m87 = new Moeda("MOP","Pataca de Macau");
		guardador.adiciona(m87);
		
		Moeda m88 = new Moeda("MRO","Ouguiya Mauritana");
		guardador.adiciona(m88);
		
		Moeda m89 = new Moeda("MUR","Rupia Mauriciana");
		guardador.adiciona(m89);
		
		Moeda m90 = new Moeda("MVR","Rufiyaa Maldiva");
		guardador.adiciona(m90);
		
		Moeda m91 = new Moeda("MWK","Kwacha Malauiana");
		guardador.adiciona(m91);
		
		Moeda m92 = new Moeda("MXN","Peso Mexicano");
		guardador.adiciona(m92);
		
		Moeda m93 = new Moeda("MYR","Ringgit Malaio");
		guardador.adiciona(m93);
		
		Moeda m94 = new Moeda("MZN","Metical de Moçambique");
		guardador.adiciona(m94);
		
		Moeda m95 = new Moeda("NAD","Dolar Namibio");
		guardador.adiciona(m95);
		
		Moeda m96 = new Moeda("NGN","Naira Nigeriana");
		guardador.adiciona(m96);
		
		Moeda m97 = new Moeda("NGNI","Naira Nigeriana");
		guardador.adiciona(m97);
		
		Moeda m98 = new Moeda("NGNPARALLEL","Naira Nigeriana");
		guardador.adiciona(m98);
		
		Moeda m99 = new Moeda("NIO","Cordoba Nicaraguense");
		guardador.adiciona(m99);
		
		Moeda m100 = new Moeda("NOK","Coroa Norueguesa");
		guardador.adiciona(m100);
		
		Moeda m101 = new Moeda("NPR","Rupia Nepalesa");
		guardador.adiciona(m101);
		
		Moeda m102 = new Moeda("NZD","Dolar Neozelandes");
		guardador.adiciona(m102);
		
		Moeda m103 = new Moeda("OMR","Rial Omanense");
		guardador.adiciona(m103);
		
		Moeda m104 = new Moeda("PAB","Balboa Panamenho");
		guardador.adiciona(m104);
		
		Moeda m105 = new Moeda("PEN","Sol do Peru");
		guardador.adiciona(m105);
		
		Moeda m106 = new Moeda("PGK","Kina Papua-Nova Guine");
		guardador.adiciona(m106);
		
		Moeda m107 = new Moeda("PHP","Peso Filipino");
		guardador.adiciona(m107);
		
		Moeda m108 = new Moeda("PKR","Rupia Paquistanesa");
		guardador.adiciona(m108);
		
		Moeda m109 = new Moeda("PLN","Zloti Polones");
		guardador.adiciona(m109);
		
		Moeda m110 = new Moeda("PYG","Guarani Paraguaio");
		guardador.adiciona(m110);
		
		Moeda m111 = new Moeda("QAR","Rial Catarense");
		guardador.adiciona(m111);
		
		Moeda m112 = new Moeda("RON","Leu Romeno");
		guardador.adiciona(m112);
		
		Moeda m113 = new Moeda("RSD","Dinar Servio");
		guardador.adiciona(m113);
		
		Moeda m114 = new Moeda("RUB","Rublo Russo");
		guardador.adiciona(m114);
		
		Moeda m115 = new Moeda("RUBTOD","Rublo Russo");
		guardador.adiciona(m115);
		
		Moeda m116 = new Moeda("RUBTOM","Rublo Russo");
		guardador.adiciona(m116);
		
		Moeda m117 = new Moeda("RWF","Franco Ruandes");
		guardador.adiciona(m117);
		
		Moeda m118 = new Moeda("SAR","Riyal Saudita");
		guardador.adiciona(m118);
		
		Moeda m119 = new Moeda("SCR","Rupias de Seicheles");
		guardador.adiciona(m119);
		
		Moeda m120 = new Moeda("SDG","Libra Sudanesa");
		guardador.adiciona(m120);
		
		Moeda m121 = new Moeda("SDR","DSE");
		guardador.adiciona(m121);
		
		Moeda m122 = new Moeda("SEK","Coroa Sueca");
		guardador.adiciona(m122);
		
		Moeda m123 = new Moeda("SGD","Dolar de Cingapura");
		guardador.adiciona(m123);
		
		Moeda m124 = new Moeda("SOS","Shilling Somaliano");
		guardador.adiciona(m124);
		
		Moeda m125 = new Moeda("STD","Dobra Sao Tome/Principe");
		guardador.adiciona(m125);
		
		Moeda m126 = new Moeda("SVC","Colon de El Salvador");
		guardador.adiciona(m126);
		
		Moeda m127 = new Moeda("SYP","Libra Siria");
		guardador.adiciona(m127);
		
		Moeda m128 = new Moeda("SZL","Lilangeni Suazilandes");
		guardador.adiciona(m128);
		
		Moeda m129 = new Moeda("THB","Baht Tailandes");
		guardador.adiciona(m129);
		
		Moeda m130 = new Moeda("TJS","Somoni do Tajiquistao");
		guardador.adiciona(m130);
		
		Moeda m131 = new Moeda("TMT","TMT");
		guardador.adiciona(m131);
		
		Moeda m132 = new Moeda("TND","Dinar Tunisiano");
		guardador.adiciona(m132);
		
		Moeda m133 = new Moeda("TRY","Nova Lira Turca");
		guardador.adiciona(m133);
		
		Moeda m134 = new Moeda("TTD","Dolar de Trinidad");
		guardador.adiciona(m134);
		
		Moeda m135 = new Moeda("TWD","Dolar Taiuanes");
		guardador.adiciona(m135);
		
		Moeda m136 = new Moeda("TZS","Shilling Tanzaniano");
		guardador.adiciona(m136);
		
		Moeda m137 = new Moeda("UAH","Hryvinia Ucraniana");
		guardador.adiciona(m137);
		
		Moeda m138 = new Moeda("UGX","Shilling Ugandes");
		guardador.adiciona(m138);
		
		Moeda m139 = new Moeda("USD","Dolar Americano");
		guardador.adiciona(m139);
		
		Moeda m140 = new Moeda("USDT","Dolar Americano");
		guardador.adiciona(m140);
		
		Moeda m141 = new Moeda("UYU","Peso Uruguaio");
		guardador.adiciona(m141);
		
		Moeda m142 = new Moeda("UZS","Som Uzbequistanes");
		guardador.adiciona(m142);
		
		Moeda m143 = new Moeda("VEF","Bolivar Venezuelano");
		guardador.adiciona(m143);
		
		Moeda m144 = new Moeda("VND","Dong Vietnamita");
		guardador.adiciona(m144);
		
		Moeda m145 = new Moeda("VUV","Vatu de Vanuatu");
		guardador.adiciona(m145);
		
		Moeda m146 = new Moeda("XAF","Franco CFA Central");
		guardador.adiciona(m146);
		
		Moeda m147 = new Moeda("XAGG","Prata");
		guardador.adiciona(m147);
		
		Moeda m148 = new Moeda("XBR","Brent Spot");
		guardador.adiciona(m148);
		
		Moeda m149 = new Moeda("XCD","Dolar do Caribe Oriental");
		guardador.adiciona(m149);
		
		Moeda m150 = new Moeda("XOF","Franco CFA Ocidental");
		guardador.adiciona(m150);
		
		Moeda m151 = new Moeda("XPF","Franco CFP");
		guardador.adiciona(m151);
		
		Moeda m152 = new Moeda("XRP","XRP");
		guardador.adiciona(m152);
		
		Moeda m153 = new Moeda("YER","Riyal Iemenita");
		guardador.adiciona(m153);
		
		Moeda m154 = new Moeda("ZAR","Rand Sul-Africano");
		guardador.adiciona(m154);
		
		Moeda m155 = new Moeda("ZMK","Kwacha Zambiana");
		guardador.adiciona(m155);
		
		Moeda m156 = new Moeda("ZWL","Dolar Zimbabuense");
		guardador.adiciona(m156);
		
		Moeda m157 = new Moeda("XAU","Ouro");	
		guardador.adiciona(m157);		
		
		int tamanho = guardador.getQuantidadeDeElementos();
		System.out.println(tamanho);
		

		jComboBoxMoedaOrigem = new JComboBox<String>();
		jComboBoxMoedaDestino = new JComboBox<String>();
		
		for (int i=0; i < tamanho; i++) {
				Moeda ref = guardador.getMoeda(i);
				jComboBoxMoedaOrigem.addItem(ref.getIdMoeda().toString() + " - " + ref.getDescricao().toString());
				jComboBoxMoedaDestino.addItem(ref.getIdMoeda().toString() + " - " + ref.getDescricao().toString());
		}
	}

}
