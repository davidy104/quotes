package nz.co.yellow.spider.messaging.data.support;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import nz.co.yellow.spider.messaging.data.MessageModel;
import nz.co.yellow.spider.messaging.data.ParticipantModel;
import nz.co.yellow.spider.messaging.data.support.EntityBuilder.EntityBuilderManager;
import nz.co.yellow.spider.messaging.util.MessageUtils;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

public class InitialDataSetup {
	private TransactionTemplate transactionTemplate;

	@PersistenceContext
	private EntityManager entityManager;

	private ParticipantModel participant1;

	private ParticipantModel participant2;

	private ParticipantModel participant3;

	private MessageModel message1;

	private MessageModel message2;

	private MessageModel message3;

	private MessageModel message4;

	private MessageModel message5;

	private MessageModel message6;

	private MessageModel message7;

	private MessageModel message8;

	public InitialDataSetup(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public void initialize() {
		EntityBuilderManager.setEntityManager(this.entityManager);

		this.transactionTemplate.execute(new TransactionCallback<Void>() {

			@Override
			public Void doInTransaction(TransactionStatus status) {
				if (dataIsAlreadyPresent()) {
					return null;
				}
				// create messages
				{
					InitialDataSetup.this.message1 = new MessageModelBuilder() {
						{
							create("We''re great at crap shite! Let''s make a deal",
									MessageUtils
											.strToDateIgnoreException("2013-09-24T13:54:22.152265"),
									"DRAFT");
						}
					}.build();

					InitialDataSetup.this.message2 = new MessageModelBuilder() {
						{
							create("Hello",
									MessageUtils
											.strToDateIgnoreException("2013-09-25T10:50:41.210750"),
									"DRAFT");
						}
					}.build();

					InitialDataSetup.this.message3 = new MessageModelBuilder() {
						{
							create("Awesome",
									MessageUtils
											.strToDateIgnoreException("2013-09-24T15:08:53.844026"),
									"DRAFT");
						}
					}.build();

					InitialDataSetup.this.message4 = new MessageModelBuilder() {
						{
							create("No but here''s a photo of my cat",
									MessageUtils
											.strToDateIgnoreException("2013-09-24T15:10:34.516686"),
									"DRAFT");
						}
					}.build();

					InitialDataSetup.this.message5 = new MessageModelBuilder() {
						{
							create("Is it just the one smurf needing smurfed?",
									MessageUtils
											.strToDateIgnoreException("2013-09-25T13:53:37.912217"),
									"DRAFT");
						}
					}.build();

					InitialDataSetup.this.message6 = new MessageModelBuilder() {
						{
							create(" I can smurf your smurf and also snork your snork!",
									MessageUtils
											.strToDateIgnoreException("2013-09-25T10:51:10.953296"),
									"DRAFT");
						}
					}.build();

					InitialDataSetup.this.message7 = new MessageModelBuilder() {
						{
							create("Hello yourself. Can you smurf my smurf?",
									MessageUtils
											.strToDateIgnoreException("2013-09-25T10:50:56.239853"),
									"DRAFT");
						}
					}.build();

					InitialDataSetup.this.message8 = new MessageModelBuilder() {
						{
							create(" Oh my.",
									MessageUtils
											.strToDateIgnoreException("2013-09-25T10:51:21.458554"),
									"DRAFT");
						}
					}.build();
				}
				// create participants
				{
					InitialDataSetup.this.participant1 = new ParticipantModelBuilder() {
						{
							create("9c68");
							addMessages(InitialDataSetup.this.message1,
									InitialDataSetup.this.message2);
						}
					}.build();

					InitialDataSetup.this.participant2 = new ParticipantModelBuilder() {
						{
							create("84f8");
							addMessages(InitialDataSetup.this.message3,
									InitialDataSetup.this.message4,
									InitialDataSetup.this.message7,
									InitialDataSetup.this.message8);
						}
					}.build();

					InitialDataSetup.this.participant3 = new ParticipantModelBuilder() {
						{
							create("99c9");
							addMessages(InitialDataSetup.this.message5,
									InitialDataSetup.this.message6);
						}
					}.build();
				}

				// initiate msgThread
				{
					new ThreadModelBuilder() {
						{
							create("Discussion of quote",
									MessageUtils
											.strToDateIgnoreException("2013-09-24T13:54:04.342078"),
									"IN_PROGRESS");
							addMessages(InitialDataSetup.this.message1,
									InitialDataSetup.this.message2,
									InitialDataSetup.this.message3,
									InitialDataSetup.this.message4);
							addSpiderMsgParticipant(
									InitialDataSetup.this.participant1,
									InitialDataSetup.this.participant2);
						}
					}.build();

					new ThreadModelBuilder() {
						{
							create("Discussion of quote",
									MessageUtils
											.strToDateIgnoreException("2013-09-24T14:16:15.313371"),
									"IN_PROGRESS");
							addMessages(InitialDataSetup.this.message5,
									InitialDataSetup.this.message6,
									InitialDataSetup.this.message7,
									InitialDataSetup.this.message8);
							addSpiderMsgParticipant(
									InitialDataSetup.this.participant2,
									InitialDataSetup.this.participant3);
						}
					}.build();
				}
				return null;
			}

			private boolean dataIsAlreadyPresent() {
				return InitialDataSetup.this.entityManager
						.createQuery(
								"select count(s.messageId) from MessageModel s",
								Long.class).getSingleResult().longValue() > 0;
			}
		});

		EntityBuilderManager.clearEntityManager();
	}
}
