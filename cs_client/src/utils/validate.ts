type RulesItemType = {
    required: boolean,
    minLength: number,
    maxLength: number,
    regex: RegExp,
    equal_to: string
}

type ValidateParamsType = {
    form: {
        [key: string]: any
    },
    rules: {
        [key: string]: Partial<RulesItemType>
    },
    messages?: {
        [key: string]: string
    }
}

type ValidateResult = {
    isValid: boolean,
    errors: Map<string, { message: string }>
}

type RuleMethods = {
    [key: string]: (valueInput: any, valueRule: any) => boolean
}

type MessageMethods = {
    [key: string]: (key: string) => string
}

export default function ValidateUtils(options: ValidateParamsType): ValidateResult {
    let errors: Map<string, { message: string }> = new Map();
    const { form, messages, rules } = options;

    const defaultMessages: MessageMethods = {
        required: (key: string) => {
            return `${key} can not be empty`
        },
        minLength: (key: string) => {
            return `${key} must be at least {minLength} characters long`
        },
        maxLength: (key: string) => {
            return `${key} can only contain up to {maxLength} characters`
        },
        regex: (key: string) => {
            return `${key} is not in the correct format`
        },
        equal_to: (key: string) => {
            return `${key} does not match the {equal_to}`
        }
    };

    const ruleMethods: RuleMethods = {
        required: (valueInput: any, valueRule: any) => {
            return String(valueInput).trim() !== '';
        },
        minLength: (valueInput: string, valueRule: number) => {
            return valueInput.length >= valueRule;
        },
        maxLength: (valueInput: string, valueRule: number) => {
            return valueInput.length <= valueRule;
        },
        regex: (valueInput: string, valueRule: RegExp) => {
            return valueRule.test(valueInput);
        },
        equal_to: (valueInput: string, valueRule: string) => {
            return valueInput === form[valueRule];
        }
    };

    function handleValidate() {
        errors = new Map();

        for (const keyInputName in rules) {
            const valueInput = form[keyInputName];
            const rulesForInputName = rules[keyInputName];

            for (const ruleItemKey in rulesForInputName) {
                const valueRule = rulesForInputName[ruleItemKey as keyof RulesItemType];
                const validateResult = ruleMethods[ruleItemKey](valueInput, valueRule);
                const keyMessage = keyInputName + '_' + ruleItemKey;
                const errorMessage = messages && messages[keyMessage];
                const defaultMessage = defaultMessages[ruleItemKey](keyInputName).replace(`{${ruleItemKey}}`, String(valueRule));

                if (!validateResult) {
                    errors.set(keyInputName, {
                        message: errorMessage || defaultMessage
                    })
                    break;
                }
            }
        }
    }

    handleValidate();

    if (errors.size > 0) {
        return {
            isValid: false, errors
        }
    }

    return {
        isValid: true, errors
    }
}