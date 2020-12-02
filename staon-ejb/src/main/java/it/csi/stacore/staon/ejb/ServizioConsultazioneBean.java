package it.csi.stacore.staon.ejb;

import javax.ejb.CreateException;

import it.csi.stacore.staon.dto.RegioneDto;
import it.csi.stacore.staon.interfacecsi.ServizioConsultazioneInterface;
import it.csi.stacore.staon.util.Tracer;

public class ServizioConsultazioneBean  extends CommonServiceBean  implements ServizioConsultazioneInterface{

	private static final long serialVersionUID = 1L;

	private ServizioConsultazioneInterface servizioConsultazioneInterface;

	public void ejbCreate() throws CreateException {
		String method = "ejbCreate";
		Tracer.debug(LOG,  getClass().getName(), method, "BEGIN");
		try {
			super.ejbCreate();
			if (beanFactoryReference.getFactory().containsBean("servizioConsultazione")){
				servizioConsultazioneInterface = (ServizioConsultazioneInterface) beanFactoryReference.getFactory().getBean("servizioConsultazione");
				Tracer.debug(LOG,  getClass().getName(), method, "found helper servizioConsultazione");
			}
			else
				throw new CreateException("Nessun Helper servizioConsultazione trovato nella configurazione");


		} catch (Exception e) {
			Tracer.error(LOG,  getClass().getName(), method, "Exception " + e);
			throw new CreateException(e.getMessage());
		}
		finally{
			Tracer.debug(LOG,  getClass().getName(), method, "END");
		}
	}


	/*
	 * (non-Javadoc)
	 * @see it.csi.stacore.staon.ejb.CommonBean#testResources()
	 */
	public boolean testResources() throws Exception {
		try {
			return servizioConsultazioneInterface.testResources();
		}
		catch(Exception e) {
			throw e;
		}
	}


	@Override
	public RegioneDto[] findRegione() throws Exception {
		try {
			return servizioConsultazioneInterface.findRegione();
		}
		catch(Exception e) {
			throw e;
		}
	}


}
