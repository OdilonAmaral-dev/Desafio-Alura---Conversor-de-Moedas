import java.awt.EventQueue;
import java.io.IOException;

import br.com.conversor.moeda.controller.MoedaController;
import br.com.conversor.moeda.ui.ConverteUI;

public class AppConversor {
	
	
	public static void main(String[] args) throws IOException {
				
		EventQueue.invokeLater(() -> {
			ConverteUI ex;
			try {
				ex = new ConverteUI();
				ex.setVisible(true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});		
	}
}
