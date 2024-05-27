package view;

public class Constants {

	public static final String ERROR_DIALOG_TITLE = "Erro";
	public static final String INFO_DIALOG_TITLE = "Sucesso";
	
	public static final String CREATE_CONTEXT_ERROR = "Erro ao criar o contexto";
	public static final String CREATE_CONTEXT_RECOVERY = "Corrija o nome do Contexto e tente de novo";
	public static final String CREATE_CONTEXT_MANAGER_RECOVERY = "Corrija o nome do Responsável";
	public static final String CREATE_CONTEXT_SUCCESS = "Contexto criada com sucesso";
	public static final String CREATE_CONTEXT_INVALID_CATEGORY = "Preencha o nome do contexto";
	public static final String CREATE_CONTEXT_CODE_RECOVERY = "O seguinte código não existe no sistema: ";
	public static final String CREATE_SENSORS_ADDED = "Sensores adicionados ao contexto";
	
	public static final String CREATE_CATEGORY_ERROR = "Erro ao criar a categoria";
	public static final String CREATE_CATEGORY_RECOVERY = "Corrija o nome da categoria e tente de novo";
	public static final String CREATE_CATEGORY_SUCCESS = "Categoria criada com sucesso";
	public static final String CREATE_CATEGORY_INVALID_CATEGORY = "Preencha o nome da categoria";
	public static final String LOGIN_INVALID_USERNAME = "Preencha o nome do utilizador";
	public static final String LOGIN_INVALID_PASSWORD = "Preencha a palavra-passe";
	public static final String LOGIN_FAIL_TO_LOGIN = "Utilizador ou palavra-passe inválidos";
	public static final String LOGIN_RECOVERY = "Corrija o utiliador ou a palavra-passe e tente de novo";
	public static final String NOT_YET_IMPLEMENTED = "Não implementado até esta iteração";
	public static final String CREATE_USER_INVALID_USERNAME = "Nome do utilizador não preenchido";
	public static final String CREATE_USER_INVALID_PASSWORD = "Palavra-passe não preenchida";
	public static final String CREATE_USER_INVALID_PASSWORD_AGAIN = "Palavra-passe de confirmação não preenchida";
	public static final String CREATE_USER_PASSWORD_MISMATCH = "Palavra-passe e a sua confirmação não são idênticas";
	public static final String CREATE_USER_INVALID_EMAIL = "Email inválido";
	public static final String CREATE_USER_SUCCESS = "Utilizador criado com sucesso";
	public static final String CREATE_USER_FAIL_TO_CREATE = "Não foi possível criar o utilizador";
	public static final String CREATE_USER_FAIL_TO_CREATE_RECOVERY = "Consulte o administrador de sistemas";
	public static final String CREATE_USER_ERROR_VALIDATE_RECOVERY = "Corrija os erros nos campos e tente novamente";
	public static final String USER_TYPE_GESTOR = "Gestor";
	public static final String USER_TYPE_TECNICO = "Técnico";
	public static final String USE_CASE_PRECONDITION_ERROR = "Não há nenhum utilizador autenticado na aplicação";
	public static final String USE_CASE_PRECONDITION_RECOVERY = "Autentique-se na aplicação para a poder utilizar";
	public static final String ADD_COMPATIBLE_UNIT_INVALID_DATA = "O campo unidade compatível não está preenchido";
	public static final String ADD_COMPATIBLE_UNIT_INVALID_DATA_RECOVERY = 
			"Escolha uma unidade compatível da lista de unidades disponível";
	public static final String CREATE_UNIT_SUCCESS = "Unidade criada com sucesso";
	public static final String CREATE_UNIT_UNDERWAY_ERROR = "Já existe um caso de uso de criar unidade em curso";
	public static final String CREATE_UNIT_UNDERWAY_RECOVERY = "Não pode criar mais do que uma unidade em simultâneo";
	public static final String CREATE_UNIT_INVALID_DESCRIPTION_NICK = 
			"Descrição ou abreviatura da unidade não preenchidas";
	public static final String CREATE_INDICATOR_INVALID_DESCRIPTION_NICK_RECOVERY = 
			"Tem de preencher a descrição e a abreviatura da unidade a criar";
	public static final String CREATE_CHARACTERISTIC_INVALID_NAME = "Nome da característica inválido";
	public static final String CREATE_CHARACTERISTIC_RECOVERY = "Insira outro nome e tente outra vez";
	public static final String CREATE_CHARACTERISTIC_ERROR = "Erro ao escolher opções da caracteristica";
	public static final String CREATE_CHARACTERISTIC_SUCCESS = "Caracteristica criada com sucesso";
	public static final String LINK_MANAGER_ERROR = "Erro ao escolher uma das opções";
	public static final String LINK_MANAGER_RECOVERY = "Escolha uma das opções válidas";
	public static final String LINK_MANAGER_SUCCESS = "Gestor foi adicionado com sucesso";
	public static final String SET_CONFIGURATION_INVALID_CONTEXT = "Contexto inválido";
	public static final String SET_CONFIGURATION_RECOVERY = "Introduza um valor válido";
	public static final String SET_CONFIGURATION_INVALID_CHARACTERISTIC = "Característica inválida";
	public static final String SET_CONFIGURATION_SUCCESS = "Configuração definida com sucesso";
	public static final String REGISTER_READING_INVALID_CONTEXT = "Contexto inválido";
	public static final String REGISTER_READING_RECOVERY = "Introduza um valor válido";
	public static final String REGISTER_READING_INVALID_CHARACTERISTIC = "Característica inválida";
	public static final String REGISTER_READING_SUCCESS = "Valores registados com sucesso";
	public static final String CREATE_UNIT_PERMISSION = "Não tem permissão para adicionar unidades";
	public static final String CREATE_UNIT_TECNICO = "Peça a um técnico para fazer esta operação.";
	public static final String CREATE_UNIT_GESTOR = "Deverá ser o gestor a fazer esta funcionalidade";
}
