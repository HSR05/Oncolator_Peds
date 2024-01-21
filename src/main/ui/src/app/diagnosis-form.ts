import { Injectable } from '@angular/core';
import { FormlyFieldConfig } from '@ngx-formly/core';

@Injectable({
    providedIn: 'root'
})

export class diagnosisForm {

    constructor() { }

    model0 = {
        highestWBC: '',
        enlargedLiver: '',
        enlargedLiverSize: '',
        enlargedSpleen: '',
        enlargedSpleenSize: '',
        enlargedLymphNodes: '',
        enlargedLymphNodesSize: '',
        serumLDH: '',
        mediastinalMass: '',
        extramedullaryDisease: '',
        randomization: 'A',
        otherNotes: ''
    };

    fields0: FormlyFieldConfig[] = [
        {
            key: 'highestWBC',
            type: 'input',
            templateOptions: {
                type: 'number',
                label: 'Highest WBC',
                required: true
            }
        },
        {
            key: 'enlargedLiver',
            type: 'radio',
            templateOptions: {
                label: 'Enlarged Liver (cm)',
                formCheck: 'inline',
                type: 'radio',
                required: true,
                options: [
                    { value: 'true', label: 'Yes' },
                    { value: 'false', label: 'No', }
                ]
            }
        },
        {
            key: 'enlargedLiverSize',
            type: 'input',
            templateOptions: {
                type: 'number',
                label: 'Size',
                required: true
            },
            expressionProperties: {
                'templateOptions.disabled': model => (model.enlargedLiver !== 'true')
            },
            hideExpression: model => model.enlargedLiver !== 'true'
        },
        {
            key: 'enlargedSpleen',
            type: 'radio',
            templateOptions: {
                label: 'Enlarged Spleen (cm)',
                formCheck: 'inline',
                type: 'radio',
                required: true,
                options: [
                    { value: 'true', label: 'Yes' },
                    { value: 'false', label: 'No', }
                ]
            }
        },
        {
            key: 'enlargedSpleenSize',
            type: 'input',
            templateOptions: {
                type: 'number',
                label: 'Size',
                required: true,
            },
            expressionProperties: {
                'templateOptions.disabled': model => (model.enlargedSpleen !== 'true')
            },
            hideExpression: model => model.enlargedSpleen !== 'true'
        },
        {
            key: 'enlargedLymphNodes',
            type: 'radio',
            templateOptions: {
                label: 'Enlarged Lymph Nodes',
                formCheck: 'inline',
                type: 'radio',
                required: true,
                options: [
                    { value: 'true', label: 'Yes' },
                    { value: 'false', label: 'No', }
                ]
            }
        },
        {
            key: 'enlargedLymphNodesSize',
            type: 'input',
            templateOptions: {
                type: 'number',
                label: 'Size',
                required: true,
            },
            expressionProperties: {
                'templateOptions.disabled': model => (model.enlargedLymphNodes !== 'true')
            },
            hideExpression: model => model.enlargedLymphNodes !== 'true'
        },
        {
            key: 'serumLDH',
            type: 'input',
            templateOptions: {
                type: 'number',
                label: 'Serum LDH',
                required: true
            }
        },
        {
            key: 'mediastinalMass',
            type: 'radio',
            templateOptions: {
                label: 'Mediastinal Mass',
                formCheck: 'inline',
                type: 'radio',
                required: true,
                options: [
                    { value: 'true', label: 'Yes' },
                    { value: 'false', label: 'No', }
                ]
            }
        },
        {
            key: 'extramedullaryDisease',
            type: 'radio',
            templateOptions: {
                label: 'Extramedullary Disease',
                formCheck: 'inline',
                type: 'radio',
                required: true,
                options: [
                    { value: 'true', label: 'Yes' },
                    { value: 'false', label: 'No', }
                ]
            }
        },
        /*{
            key: 'randomization',
            type: 'radio',
            defaultValue: 'A',
            templateOptions: {
                label: 'Randomization',
                disabled: true,
                formCheck: 'inline',
                type: 'radio',
                required: true,
                options: [
                    { value: 'A', label: 'A' },
                    { value: 'B', label: 'B', }
                ]
            }
        },*/
        {
            key: 'randomization'
        },
        {
            key: 'otherNotes',
            type: 'input',
            templateOptions: {
                label: 'Other Notes (if any)',
                type: 'text'
            }
        }
    ];


    model1 = {
        prednisoloneResponse: '',
        CNSDisease: '',
        cytogenetics: ''
    };

    fields1: FormlyFieldConfig[] = [
        {
            key: 'prednisoloneResponse',
            type: 'radio',
            templateOptions: {
                label: 'Prednisolone Response on Day 8',
                type: 'radio',
                required: true,
                options: [
                    { value: 'good', label: 'Good (Peripheral blast count < ...)' },
                    { value: 'bad', label: 'Bad (Peripheral blast count > ...)', }
                ]
            }
        },
        {
            key: 'CNSDisease',
            type: 'radio',
            templateOptions: {
                label: 'CNS Disease',
                formCheck: 'inline',
                type: 'radio',
                required: true,
                options: [
                    { value: 'true', label: 'Yes' },
                    { value: 'false', label: 'No', }
                ]
            }
        },
        {
            key: 'cytogenetics',
            type: 'select',
            templateOptions: {
                label: 'Cytogenetics',
                required: true,
                options: [
                    { label: 'BCR-ABL', value: 'BCR_ABL' },
                    { label: 'MLL Rearrangement', value: 'MLL_Rearrangement' },
                    { label: 'ETV6-RUNX1', value: 'ETV6_RUNX1' },
                    { label: 'None', value: 'None' }
                ]
            }
        }
    ]

    model2 = {
        boneMarrowMicroscopy: '',
        MRDStatus: '',
        CNSStatus: '',
        completeRemission: ''
    };

    fields2: FormlyFieldConfig[] = [
        {
            key: 'boneMarrowMicroscopy',
            type: 'select',
            templateOptions: {
                label: 'Bone Marrow microscopy',
                required: true,
                options: [
                    { label: '<5% blasts', value: '0' },
                    { label: '5-25% blasts', value: '1' },
                    { label: '>25% blasts', value: '2' }
                ]
            }
        },
        {
            key: 'MRDStatus',
            type: 'radio',
            templateOptions: {
                label: 'MRD Status > 10^(-4)',
                formCheck: 'inline',
                type: 'radio',
                required: true,
                options: [
                    { value: 'true', label: 'Yes' },
                    { value: 'false', label: 'No', }
                ]
            }
        },
        {
            key: 'CNSStatus',
            type: 'radio',
            templateOptions: {
                label: 'CNS Status (if previously CNS disease)',
                formCheck: 'inline',
                type: 'radio',
                required: true,
                options: [
                    { value: 'true', label: 'Yes' },
                    { value: 'false', label: 'No', }
                ]
            }
        },
        {
            key: 'completeRemission',
            type: 'radio',
            templateOptions: {
                label: 'Complete Remission',
                formCheck: 'inline',
                type: 'radio',
                required: true,
                options: [
                    { value: 'true', label: 'Yes' },
                    { value: 'false', label: 'No', }
                ]
            }
        }
    ]

    model3 = {
        testicularDisease: '',
        mediastinalMass: '',
        boneMarrowMorphology: ''
    };

    fields3: FormlyFieldConfig[] = [
        {
            key: 'testicularDisease',
            type: 'radio',
            templateOptions: {
                label: 'Testicular Disease (if Testicular disease at diagnosis)',
                formCheck: 'inline',
                type: 'radio',
                required: true,
                options: [
                    { value: 'true', label: 'Yes' },
                    { value: 'false', label: 'No', }
                ]
            }
        },
        {
            key: 'mediastinalMass',
            type: 'radio',
            templateOptions: {
                label: 'Mediastinal Mass (if Mediastinal Mass at diagnosis)',
                formCheck: 'inline',
                type: 'radio',
                required: true,
                options: [
                    { value: 'true', label: 'Yes' },
                    { value: 'false', label: 'No', }
                ]
            }
        },
        {
            key: 'boneMarrowMorphology',
            type: 'radio',
            templateOptions: {
                label: 'Bone Marrow morphology (if patient is not in Complete Remission post Induction)',
                formCheck: 'inline',
                type: 'radio',
                required: true,
                options: [
                    { value: 'normal', label: 'Normal' },
                    { value: 'abnormal', label: 'Abnormal', }
                ]
            }
        }
    ]
}